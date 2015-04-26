// vendor.js

var REST_DATA = 'api/vendorlist';
var KEY_ENTER = 13;

function loadItems(){
	xhrGet(REST_DATA, function(data){
		document.getElementById("loading").innerHTML = "";
		var receivedItems = data.body || [];
		var items = [];
		var i;
		// Make sure the received items have correct format
		for(i = 0; i < receivedItems.length; ++i){
			var item = receivedItems[i];
			if(item && 'id' in item && 'name' in item){
				items.push(item);
			}
		}
		for(i = 0; i < items.length; ++i){
			addItem(items[i], false);
		}
	}, function(err){
		console.error(err);
		document.getElementById("loading").innerHTML = "ERROR";
	});
}

function addItem(item, isNew){
	var row = document.createElement('tr');
	var id = item && item.id;
	if(id){
		row.setAttribute('data-id', id);
	}
	row.innerHTML = "<td style='width:18%'><textarea placeholder=\"Name...\" onchange='saveChange(this)' onkeydown='onKey(event)'></textarea></td>" +
		"<td style='width:18%'><textarea placeholder=\"City...\" onchange='saveChange(this)' onkeydown='onKey(event)'></textarea></td>" + 
		"<td style='width:18%'><textarea placeholder=\"Latitude...\" onchange='saveChange(this)' onkeydown='onKey(event)'></textarea></td>" + 
		"<td style='width:18%'><textarea placeholder=\"Longitude...\" onchange='saveChange(this)' onkeydown='onKey(event)'></textarea></td>" + 
		"<td style='width:18%'><textarea placeholder=\"Phone...\" onchange='saveChange(this)' onkeydown='onKey(event)'></textarea></td>" + 
		"<td class='deleteBtn' onclick='deleteItem(this)' title='delete me'></td>";
	var table = document.getElementById('notes');
	console.log(table.lastChild);
	table.lastChild.appendChild(row);
	var textareaName = row.childNodes[0].firstChild;
	var textareaCity = row.childNodes[1].firstChild;
	var textareaLat = row.childNodes[2].firstChild;
	var textareaLong = row.childNodes[3].firstChild;
	var textareaPhone = row.childNodes[4].firstChild;
	if(item){
		textareaName.value = item.name;
		textareaCity.value = item.city;
		textareaLat.value = item.latitude;
		textareaLong.value = item.longitude;
		textareaPhone.value = item.phone;
	}
	row.isNew = !item || isNew;
	textareaName.focus();
}

function deleteItem(deleteBtnNode){
	var row = deleteBtnNode.parentNode;
	row.parentNode.removeChild(row);
	xhrDelete(REST_DATA + '?id=' + row.getAttribute('data-id'), function(){
	}, function(err){
		console.error(err);
	});
}

function onKey(evt){
	if(evt.keyCode == KEY_ENTER && !evt.shiftKey){
		evt.stopPropagation();
		evt.preventDefault();
		var row = evt.target.parentNode.parentNode;
		if(row.nextSibling){
			row.nextSibling.firstChild.firstChild.focus();
		}else{
			addItem();
		}
	}
}

function saveChange(contentNode, callback){
	var row = contentNode.parentNode.parentNode;
	var name = row.childNodes[0].firstChild.value;
	var city = row.childNodes[1].firstChild.value;
	var latitude = row.childNodes[2].firstChild.value;
	var longitude = row.childNodes[3].firstChild.value;
	var phone = row.childNodes[4].firstChild.value;
	var data = {
		name: name, 
		city: city, 
		latitude: latitude,
		longitude: longitude,
		phone: phone
	};
	if(row.isNew){
		delete row.isNew;
		xhrPost(REST_DATA, data, function(item){
			row.setAttribute('data-id', item.id);
			callback && callback();
		}, function(err){
			console.error(err);
		});
	}else{
		data.id = row.getAttribute('data-id');
		xhrPut(REST_DATA, data, function(){
			console.log('updated: ', data);
		}, function(err){
			console.error(err);
		});
	}
}

function toggleServiceInfo(){
	var node = document.getElementById('dbserviceinfo');
	node.style.display = node.style.display == 'none' ? '' : 'none';
}


loadItems();

