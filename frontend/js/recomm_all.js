document.addEventListener("DOMContentLoaded", function () {
  const userNameEl = document.getElementById("user-name");
  const userName = localStorage.getItem("userName");
  if (userNameEl && userName) {
    userNameEl.textContent = `${userName}`;
  }

  const userId = Number(localStorage.getItem("userId"));
  if (!userId) {
    alert("로그인이 필요합니다.");
    window.location.href = "login.html";
    return;
  }

  fetch("http://localhost:8080/api/matched-rooms", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ userId }),
  })
    .then((res) => res.json())
    .then((rooms) => {
      console.log("받은 매물 리스트:", rooms);
      const grid = document.getElementById("listing-grid");
      grid.innerHTML = ""; // 초기화

      if (rooms.length === 0) {
        grid.innerHTML = "<p>조건에 맞는 매물이 없습니다.</p>";
        return;
      }

      rooms.forEach((room) => {
        const card = document.createElement("div");
        card.className = "listing-card";
        card.innerHTML = `
          <h3>${room.buildingName}</h3>
          <p class="location">${room.guName} ${room.dongName}</p>
          <p class="price"><strong>${room.deposit}/${room.monthlyRent}</strong></p>
        `;
        grid.appendChild(card);
      });
    })
    .catch((err) => {
      console.error("매물 로딩 실패:", err);
      alert("매물 데이터를 불러오지 못했습니다.");
    });

  // 하단 버튼 이동 처리
  const viewAllReturnBtn = document.querySelector(".view-all-return");
  const navButton = document.querySelector(".nav-button");
  const myButton = document.querySelector(".my-button");
  const leftButton = document.querySelector(".left-button");

  if (viewAllReturnBtn) {
    viewAllReturnBtn.addEventListener("click", () => {
      window.location.href = "money_input.html";
    });
  }
  if (navButton) {
    navButton.addEventListener("click", () => {
      window.location.href = "money_input.html";
    });
  }
  if (myButton) {
    myButton.addEventListener("click", () => {
      window.location.href = "mypage.html";
    });
  }
  if (leftButton) {
    leftButton.addEventListener("click", () => {
      window.location.href = "analysis.html";
    });
  }
});
