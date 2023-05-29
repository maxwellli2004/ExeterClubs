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

function outsideClickHandler(event) {
  const clubForm = document.getElementById("dateForm");
  // check if the clicked element is the popup form or a child of the form
  if (!clubForm.contains(event.target)) {
    closeDate();
  }
}

var months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];
var currentMonthIndex = 4; // may is at index 4
var currentYear = 2023;

function nextMonth() {
  currentMonthIndex++; // increment the current month index

  // check if the index exceeds the length of the months array
  if (currentMonthIndex >= months.length) {
    currentMonthIndex = 0; // reset back to the first month
    currentYear++; // increment the year
  }

  // update the text content of the dynamicMonth element
  var dynamicMonth = document.getElementById("dynamicMonth");
  dynamicMonth.innerHTML =
    months[currentMonthIndex] +
    " " +
    currentYear +
    '<span style="font-size: 18px">';

  // generate buttons for the new month
  generateCalendarDates();
}

function prevMonth() {
  currentMonthIndex--; // decrement the current month index

  // check if the index goes below 0
  if (currentMonthIndex < 0) {
    currentMonthIndex = months.length - 1; // set it to the last month
    currentYear--; // decrement the year
  }

  // update the text content of the dynamicMonth element
  var dynamicMonth = document.getElementById("dynamicMonth");
  dynamicMonth.innerHTML =
    months[currentMonthIndex] +
    " " +
    currentYear +
    '<span style="font-size: 18px">';

  // generate buttons for the new month
  generateCalendarDates();
}

function generateCalendarDates() {
  var daysContainer = document.querySelector(".days");
  daysContainer.innerHTML = "";

  var totalDays = getDaysInMonth(currentYear, currentMonthIndex);
  for (var i = 1; i <= totalDays; i++) {
    var div = document.createElement("div");
    div.classList.add("dateSquare");

    var dateNumber = document.createElement("span");
    dateNumber.textContent = i;
    div.appendChild(dateNumber);

    var eventsContainer = document.createElement("div");
    eventsContainer.classList.add("dateEventContainer");

    var event1 = createEventLink("Event 1", "./eventPage.html");
    eventsContainer.appendChild(event1);

    var event2 = createEventLink("Event 2", "./eventPage.html");
    eventsContainer.appendChild(event2);

    var event3 = createEventLink("Event 3", "./eventPage.html");
    eventsContainer.appendChild(event3);

    var event4 = createEventLink("Event 4", "./eventPage.html");
    eventsContainer.appendChild(event4);

    div.appendChild(eventsContainer);
    daysContainer.appendChild(div);
  }
}

function createEventLink(text, url) {
  var eventLink = document.createElement("a");
  eventLink.classList.add("dateEvent");
  eventLink.href = url;
  eventLink.textContent = text;
  eventLink.appendChild(document.createElement("br")); // Add line break after each event link
  return eventLink;
}

function getDaysInMonth(year, month) {
  return new Date(year, month + 1, 0).getDate();
}
