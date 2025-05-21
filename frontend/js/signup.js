// signup.js
document.addEventListener("DOMContentLoaded", function () {
    // 기존 체크박스 연결 코드 (생략 가능)
    document.addEventListener("DOMContentLoaded", function () {
        // "모두 동의" 체크박스
        const masterCheck = document.getElementById("agree-all");
      
        // 하위 약관 체크박스들
        const subChecks = document.querySelectorAll(".sub-agree");
      
        // "모두 동의" 클릭 시
        masterCheck.addEventListener("change", function () {
          subChecks.forEach(cb => {
            cb.checked = masterCheck.checked;
          });
        });
      
        // 하위 체크박스 상태에 따라 "모두 동의" 자동 체크/해제
        subChecks.forEach(cb => {
          cb.addEventListener("change", function () {
            const allChecked = Array.from(subChecks).every(cb => cb.checked);
            masterCheck.checked = allChecked;
          });
        });
      });
  
    // 회원가입 폼 제출 시 메인 페이지로 이동
    const form = document.querySelector(".signup-form");
  
    form.addEventListener("submit", function (e) {
      e.preventDefault(); // 실제 서버 제출 막기
  
      // 모든 필수 입력값이 채워졌다고 가정하고 바로 이동
      window.location.href = "login.html"; // 로그인 페이지로 이동
    });
  });
  