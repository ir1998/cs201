/**
 * Create the FactoryWorkbench on the frontend
 */

function FactoryWorkbench(cell, workbench) {
	// create and place image
	var name = workbench['name'];
	var img = document.createElement('img');
	img.id = name;
	img.src = 'img/' + workbench.image;
	//append it to the bottom right grid cell
	cell.appendChild(img);
	
	this.createLabel(cell, name);
}

FactoryWorkbench.prototype.createLabel = function (cell, name) {
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