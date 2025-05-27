document.addEventListener("DOMContentLoaded", function () {
  // 사용자 이름 출력
  const userName = localStorage.getItem("userName");
  const headline = document.querySelector(".headline");
  if (userName && headline) {
    headline.textContent = `반가워요, ${userName}님!`;
  }

  // 페이지 이동 버튼들
  document.querySelector(".go-btn")?.addEventListener("click", () => {
    window.location.href = "money_input.html";
  });

  document.querySelector(".nav-button")?.addEventListener("click", () => {
    window.location.href = "money_input.html";
  });

  document.querySelector(".my-button")?.addEventListener("click", () => {
    window.location.href = "mypage.html";
  });

  document.querySelector(".left-button")?.addEventListener("click", () => {
    window.location.href = "analysis.html";
  });
});
