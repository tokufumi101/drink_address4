package com.example.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.AddressDao;
import com.example.demo.dao.DrinkDao;
import com.example.demo.dto.DrinkDto;
import com.example.demo.entity.AddressEnt;
import com.example.demo.entity.DrinkEnt;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DrinkService {
	@Autowired DrinkDao drinkRepository;
	@Autowired AddressDao addressRepository;
	
	public void top(Model model) throws Exception {
		List list = drinkRepository.findAll();
		model.addAttribute("data", list);
		List addressList = addressRepository.findAll();
		model.addAttribute("tableData", addressList);
	}
	
	public void register(String name, DrinkEnt drinkEnt, AddressEnt addressEnt,
			DrinkDto drinkDto,RedirectAttributes redirectAttributes) {
		if (!drinkRepository.existsByName(name)) {
			DrinkEnt drinkPlus = new DrinkEnt(name);
			drinkPlus.setId(drinkRepository.findAll().size() + 1);
			drinkRepository.saveAndFlush(drinkPlus);
		}

		addressEnt.setAddress(drinkDto.getAddress());

		// timestampを入れる
		addressEnt.setRegisterDate(new Timestamp(System.currentTimeMillis()));

		DrinkEnt drink = drinkRepository.findByName(name);

		addressEnt.setDrinkEnt(drink);

		// データがそろったのでテーブルに登録

		String address = addressEnt.getAddress();
		String makeUrl = "https://msearch.gsi.go.jp/address-search/AddressSearch?q=";
		String sQuote = null;
		String result = "";
		JsonNode root = null;
		try {
			sQuote = URLEncoder.encode(address, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String a = makeUrl + sQuote;
		try {
			URL url = new URL(a);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect(); // URL接続
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String tmp = "";
			while ((tmp = in.readLine()) != null) {
				result += tmp;
			}
			ObjectMapper mapper = new ObjectMapper();
			root = mapper.readTree(result);
			in.close();
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
		double lat = root.get(0).get("geometry").get("coordinates").get(0).asDouble();
		double lon = root.get(0).get("geometry").get("coordinates").get(1).asDouble();
		addressEnt.setLatitude(lat);
		addressEnt.setLongitude(lon);
		addressRepository.saveAndFlush(addressEnt);
		redirectAttributes.addFlashAttribute("flashmsg", "登録完了しました");
		}catch(NullPointerException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("flashmsg", "入力値が無効です");
		}
	}
	
//検索処理
	public void select(Model model, String id) {
		List selectedList = addressRepository.findByDrinkEnt(id);
		model.addAttribute("selectedList", selectedList);
		List list = drinkRepository.findAll();
		model.addAttribute("data", list);
	}	
	
}
