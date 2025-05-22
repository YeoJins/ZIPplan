document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector(".login-form");
  
    if (!form) {
      console.error("폼을 찾을 수 없습니다.");
      return;
    }
  
    form.addEventListener("submit", function (e) {
      e.preventDefault(); // 기본 동작 막기
      window.location.href = "mypage.html"; // 안전하게 이동
    });
  });
  