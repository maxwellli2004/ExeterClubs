class Header extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = `
        <!-- top bar menu -->
        <div class="top-bar">
          <div class="menu-button" onclick="toggleSidebar()">&#9776;</div>
          <div class="logo">
            <a class="logo" th:href="@{/}">ExeterClubs</a>
          </div>
          <div class="menu">
            <a th:href="@{/club/create}">Create Club +</a>
            <a href="#">Settings</a>
            <a href="#"> FAQ</a>
            <a href="#">Contact</a>
            <!-- button to open modal -->
            <form th:action="@{/signOut}" method="post" th:if="${authenticated}" id="signOut">
              <input type="submit" value="Sign Out">
            </form>
            <a th:href="@{/authenticate}" th:if="${!authenticated}">Sign In</a>
          </div>
        </div>
      
          <!-- sidebar -->
          <div class="sidebar" id="sidebar">
            <a href="#" class="close-btn" onclick="toggleSidebar()">&times;</a>
            <div class="search-container">
              <input
                type="text"
                placeholder="Search.."
                id="myInput"
                onkeyup="dropDownFunction()"
              />
            </div>
            <ul id="dropdownItems">
              <li th:each="club : ${clubs}">
                <label class="dropdownItem">
                  <input type="checkbox" class="name-checkbox" />
                  <a th:href="@{/clubDetail/{id}(id=${club.id})}"><span th:text="${club.name}"></span></a>
                  <button class="openClub-button" onclick="openClub(this)">
                    &#8942;
                  </button>
                </label>
              </li>
            </ul>
          </div>
      
          <!--club popup-->
          <div class="club-popup" id="clubForm">
            <div class="clubForm-container">
              <h2>Receive:</h2>
              <div>
                <label>
                  <input
                    type="checkbox"
                    name="all"
                    onclick="if(this.checked){signUpAll();}else{signOffAll();}"
                  />
                  All:
                </label>
              </div>
              <div>
                <label>
                  <input
                    type="checkbox"
                    name="emails"
                    onclick="if(this.checked){signUpEmail();}else{signOffEmails();}"
                  />
                  Emails:
                </label>
              </div>
              <div>
                <label>
                  <input
                    type="checkbox"
                    name="meetings"
                    onclick="if(this.checked){signUpMeetings();}else{signOffMeetings();}"
                  />
                  Meetings:
                </label>
              </div>
              <div>
                <label>
                  <input
                    type="checkbox"
                    name="events"
                    onclick="if(this.checked){signUpEvents();}else{signOffEvents();}"
                  />
                  Events:
                </label>
              </div>
              <button type="button" class="btn cancel" onclick="closeClub()">
                Close
              </button>
            </div>
          </div>
        `;
    }
}

customElements.define('nav-bar', Header);
