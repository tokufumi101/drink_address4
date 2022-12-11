package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.AddressDao;
import com.example.demo.dao.DrinkDao;
import com.example.demo.dto.DrinkDto;
import com.example.demo.entity.AddressEnt;
import com.example.demo.entity.DrinkEnt;
import com.example.demo.service.DrinkService;

@Controller
public class DrinkController {

	@Autowired
	DrinkDao drinkRepository;
	@Autowired
	AddressDao addressRepository;
	@Autowired
	DrinkService drinkService;
	
	/*
	 * トップ画面の描写
	 * @param model:画面にdrinkaddressテーブルのデータを描画するため
	 * @return トップ画面
	 */
	@GetMapping("/top")
	public String top(Model model) throws Exception {
		drinkService.top(model);
		return "top";
	}
	
	/*
	 * 登録処理
	 * @param name:ドリンク名
	 * @param drinkEnt:すでに登録のあるドリンク名かどうかチェックするため
	 * @param addressEnt:住所登録のため
	 * @param drinkDto:入力された住所を受け取るため
	 * @param redirectAttributes:フラッシュメッセージ出力のため
	 * @return トップ画面
	 */	
	@PostMapping("/top")
	public String add(@RequestParam("name") String name, DrinkEnt drinkEnt, AddressEnt addressEnt,
			@ModelAttribute DrinkDto drinkDto, RedirectAttributes redirectAttributes) {
			drinkService.register(name, drinkEnt, addressEnt, drinkDto,redirectAttributes);
			return "redirect:/top";
	}

//	@PostMapping(value="/top",params="select")
//	public String select() {
//		return "redirect:/select";
//	}
//	@GetMapping("/select")
//	public String select_init(Model model, 
//			@RequestParam(name = "id,name", value = "id,name", required = false) String idName) {
//		String[] splitedIdName = idName.split(",");
//
//		DrinkEnt drinkEnt = new DrinkEnt();
//		long splitedId = Long.parseLong(splitedIdName[0]);
//		drinkEnt.setId(splitedId);
//		drinkEnt.setName(splitedIdName[1]);
//		List selectedList = addressRepository.findByDrinkEnt(drinkEnt);
//		model.addAttribute("selectedList", selectedList);
//		List list = drinkRepository.findAll();
//		model.addAttribute("data", list);
//		return "list";
//	}
	
	/*
	 * 検索処理
	 * @return 検索結果画面
	 */	
	@PostMapping("/select/")
	public String select(Model model, @RequestParam("id") String id) {
		System.out.println(id);
		return "redirect:/select/"+id;
	}
	
	/*
	 * 検索処理（GETで再読み込みされた際の処理。POSTと変わらない。）
	 * @return 検索結果画面
	 */	
	@GetMapping("/select/{id}")
	public String search(Model model,@PathVariable("id") String id) {
		drinkService.select(model,id);
		return "list";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("Id") Long Id, @RequestParam("id,name") String idName,
			RedirectAttributes redirectAttributes) {
		addressRepository.deleteById(Id);
		redirectAttributes.addAttribute("id,name", idName);
		return "redirect:/select";
	}

}