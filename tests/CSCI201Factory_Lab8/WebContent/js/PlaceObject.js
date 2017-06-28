/**
 * Parent prototype to place any object on the factory grid
 */

function PlaceObject(cell, object) {
	// create and place image
	var name = object['name'];
	var img = document.createElement('img');
	img.id = name;
	img.src = 'img/' + object.image;
	//append it to the bottom right grid cell
	cell.appendChild(img);
	
	this.createLabel(cell, name);
}

PlaceObject.prototype.createLabel = function (cell, name) {
	// create label
	var label = document.createElement('span');
	label.className = 'factory-label';
	label.innerHTML = name;
	label.style.display = 'none';
	cell.appendChild(label);

	// event listeners
	cell.addEventListener('mouseover', function () {
		label.style.display = 'block';
	});
	cell.addEventListener('mouseout', function () {
		label.style.display = 'none';
	});
}