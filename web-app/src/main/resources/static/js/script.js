function toggleSidebar() {
  document.getElementById("sidebar").classList.toggle("active");
  document.getElementById("overlay").classList.toggle("active");
}

function dropDownFunction() {
  var input, filter, ul, li, a, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  ul = document.getElementById("dropdownItems");
  li = ul.getElementsByTagName("li");

  // loop through all list items, and hide those who don't match the search query
  for (i = 0; i < li.length; i++) {
    a = li[i].getElementsByTagName("a")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      li[i].style.display = "";
    } else {
      li[i].style.display = "none";
    }
  }

  // check the checkboxes for the visible names
  var nameCheckboxes = document.getElementsByClassName("name-checkbox");
  for (i = 0; i < nameCheckboxes.length; i++) {
    var name = nameCheckboxes[i].nextElementSibling.textContent.toUpperCase();
    if (name.indexOf(filter) > -1) {
      nameCheckboxes[i].style.display = "";
    } else {
      nameCheckboxes[i].style.display = "none";
    }
  }
}

function popUp() {
  var popup = document.getElementById("myPopup");
  popup.classList.toggle("show");
}

function openClub(button) {
  // get the button position relative to the viewport
  const buttonRect = button.getBoundingClientRect();
  // get the popup form element
  const clubForm = document.getElementById("clubForm");
  // position the popup form next to the button
  clubForm.style.left = `${buttonRect.right + 10}px`;
  clubForm.style.top = `${buttonRect.top}px`;
  // show the popup form
  clubForm.style.display = "block";
}

/*function openClub() {
  document.getElementById("clubForm").style.display = "block";
}*/

function closeClub() {
  document.getElementById("clubForm").style.display = "none";
}

function openDate(button) {
  // get the button position relative to the viewport
  const buttonRect = button.getBoundingClientRect();
  // get the popup form element
  const clubForm = document.getElementById("dateForm");
  // position the popup form next to the button
  clubForm.style.left = `${buttonRect.right + 10}px`;
  clubForm.style.top = `${buttonRect.top}px`;
  // show the popup form
  clubForm.style.display = "block";
}

function closeDate() {
  document.getElementById("dateForm").style.display = "none";
}