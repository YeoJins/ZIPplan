document.addEventListener("DOMContentLoaded", function () {
  // 🔥 서버에서 평균 월세 데이터 가져오기
  fetch("http://localhost:8080/api/average-rent")
    .then((res) => res.json())
    .then((data) => {
      console.log("서버 응답:", data);

      // 1. 평균 카드에 데이터 반영
      const cards = document.querySelectorAll(".district-card");
      data.forEach((item, index) => {
        if (cards[index]) {
          cards[index].querySelector("p").textContent = item.guName;
          cards[index].querySelector(
            "strong"
          ).textContent = `${item.averageRent.toLocaleString()}만원`;
        }
      });

      // 2. 순위 리스트에 반영
      const rankingList = document.querySelector(".district-ranking ul");
      rankingList.innerHTML = "";
      data.forEach((item, i) => {
        const li = document.createElement("li");
        li.innerHTML = `
          <span class="rank-number">${i + 1}</span>
          <span>${item.guName}</span>
          <small>평균 월세: ${item.averageRent.toLocaleString()}만원</small>
        `;
        rankingList.appendChild(li);
      });
    })
    .catch((err) => {
      console.error("서버 연결 실패:", err);
    });

  // 🔥 서버에서 Top 건물 데이터 가져오기
  fetch("http://localhost:8080/api/top-buildings")
    .then((res) => res.json())
    .then((data) => {
      console.log("Top buildings:", data);

      const scrollBox = document.querySelector(".product-scroll");
      scrollBox.innerHTML = ""; // 초기화

      data.forEach((item, i) => {
        const card = document.createElement("div");
        card.classList.add("product-card");

        // 태그 지정
        if (i === 0) card.classList.add("premium");
        else if (i === 1) card.classList.add("popular");
        else card.classList.add("affordable");

        card.innerHTML = `
          <span class="tag">${
            i === 0 ? "Premium" : i === 1 ? "Popular" : "Affordable"
          }</span>
          <h4>${item.buildingName}</h4>
          <p class="brand">${item.guName}</p>
          <p class="price">${item.averageRent.toLocaleString()}만원/month</p>
        `;

        scrollBox.appendChild(card);
      });
    })
    .catch((err) => {
      console.error("Top buildings fetch error:", err);
    });

  const viewAllBtn = document.querySelector(".view-all");
  const viewAllReturnBtn = document.querySelector(".view-all-return");
  const navButton = document.querySelector(".nav-button");
  const myButton = document.querySelector(".my-button");
  const leftButton = document.querySelector(".left-button");

  if (viewAllBtn) {
    viewAllBtn.addEventListener("click", () => {
      window.location.href = "recomm_all.html";
    });
  }

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
