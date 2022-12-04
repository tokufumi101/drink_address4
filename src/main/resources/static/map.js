function initMap() {
	let map;
	var marker = [];
	currentLocation = new google.maps.LatLng(36.689614, 139.691585);
	/*currentLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);*/
	var opts = {
		zoom: 7,
		center: currentLocation
	};
	map = new google.maps.Map(document.getElementById("map"), opts);
	var infoWindow = [];
	var table = document.getElementsByClassName('tableData');
	console.log(table);
	for (let i = 0; i < table.length; i++) {
		//markerLatLng = new google.maps.LatLng({lat: parseFloat(table[i].childNodes[11].textContent), lng: parseFloat(table[i].childNodes[9].textContent)});
		var latitude = parseFloat(table[i].childNodes[11].textContent);
		var longitude = parseFloat(table[i].childNodes[9].textContent);
		var myLatLng = { lat: latitude, lng: longitude };
		console.log(latitude);
		marker[i] = new google.maps.Marker({ // マーカーの追加
			position: myLatLng, // マーカーを立てる位置を指定
			map: map // マーカーを立てる地図を指定
		});
		infoWindow[i] = new google.maps.InfoWindow({
			/*content:document.getElementById("drinkName").childNodes.values.toString*/
			content: '<div class="sample">' + table[i].childNodes[5].textContent + '</div>'
		})
		markerEvent(i);//マーカーにクリックイベントを追加
	}
	function markerEvent(i) {
		marker[i].addListener('click', function() {
			infoWindow[i].open(map, marker[i]);
		});
	}
	document.getElementById("btn").onclick = function() {
		// 位置情報を取得する
		navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
	};
	var latitude;
	var longitude;
	function successCallback(position) {
		latitude = position.coords.latitude;
		longitude = position.coords.longitude;


		//緯度・経度をLatLngクラスに変換します。
		var latLngInput = new google.maps.LatLng(latitude, longitude);

		//Google Maps APIのジオコーダを使います。
		var geocoder = new google.maps.Geocoder();

		//ジオコーダのgeocodeを実行します。
		//第１引数のリクエストパラメータにlatLngプロパティを設定します。
		//第２引数はコールバック関数です。取得結果を処理します。
		console.log(latLngInput);
		geocoder.geocode(
			{
				latLng: latLngInput
			},
			function(results, status) {

				var address = "";

				if (status == google.maps.GeocoderStatus.OK) {
					//取得が成功した場合

					//住所を取得します。
					address = results[0].formatted_address;

				} else if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
					alert("住所が見つかりませんでした。");
				} else if (status == google.maps.GeocoderStatus.ERROR) {
					alert("サーバ接続に失敗しました。");
				} else if (status == google.maps.GeocoderStatus.INVALID_REQUEST) {
					alert("リクエストが無効でした。");
				} else if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT) {
					alert("リクエストの制限回数を超えました。");
				} else if (status == google.maps.GeocoderStatus.REQUEST_DENIED) {
					alert("サービスが使えない状態でした。");
				} else if (status == google.maps.GeocoderStatus.UNKNOWN_ERROR) {
					alert("原因不明のエラーが発生しました。");
				}

				//住所の結果表示をします。
				var formAddress = address.substr(address.indexOf('〒') + 10);
				document.getElementById('addressOutput').value = formAddress;
			});

      map.panTo(new google.maps.LatLng(latitude,longitude));
	};

	function errorCallback(error) {
		alert("位置情報が取得できませんでした");
	};

}



