document.addEventListener("DOMContentLoaded", function () {
  // 사용자 이름 표시
  const userNameEl = document.getElementById("user-name");
  const userName = localStorage.getItem("userName");
  if (userNameEl && userName) {
    userNameEl.textContent = `${userName}`;
  }

  // 사용자 ID 가져오기
  const userId = Number(localStorage.getItem("userId"));
  if (!userId) {
    alert("로그인이 필요합니다.");
    window.location.href = "login.html";
    return;
  }

  // 백엔드에 요청하여 추천 매물 가져오기
  fetch("http://localhost:8080/api/recommend-room", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ userId }),
  })
    .then((res) => res.json())
    .then((room) => {
      if (room.error) {
        alert("추천 매물을 찾을 수 없습니다.");
        return;
      }

      // 매물 정보 반영
      document.querySelector(".title").textContent = room.buildingName;
      document
        .querySelectorAll(".info-row")[0]
        .querySelector(
          "li"
        ).textContent = `${room.monthlyRent.toLocaleString()}만원`;
      document
        .querySelectorAll(".info-row")[1]
        .querySelector(
          "li"
        ).textContent = `${room.deposit.toLocaleString()}만원`;
      document
        .querySelectorAll(".info-row")[2]
        .querySelector("li").textContent = `${room.guName} ${room.dongName}`;

      // 특징
      const featureList = document.getElementById("feature-list");
      featureList.innerHTML = ""; // 초기화

      const floorText = `${room.floor}층`;
      const roomNumText = `${room.roomNum}호`;

      [floorText, roomNumText].forEach((txt) => {
        const li = document.createElement("li");
        li.textContent = txt;
        featureList.appendChild(li);
      });
    })
    .catch((err) => {
      console.error("추천 방 로딩 실패:", err);
    });
});

// 버튼 이동 처리
const viewAllBtn = document.querySelector(".view-all");
const navButton = document.querySelector(".nav-button");
const myButton = document.querySelector(".my-button");
const leftButton = document.querySelector(".left-button");

if (viewAllBtn) {
  viewAllBtn.addEventListener("click", () => {
    window.location.href = "recomm_all.html";
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
