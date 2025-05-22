  // 페이지가 로드되었을 때 실행
  document.addEventListener("DOMContentLoaded", function () {
    const agreeAll = document.getElementById("agree-all");
    const subAgrees = document.querySelectorAll(".sub-agree");

    // 전체 동의 체크박스 클릭 시
    agreeAll.addEventListener("change", function () {
      subAgrees.forEach(function (checkbox) {
        checkbox.checked = agreeAll.checked;
      });
    });

    // 하위 체크박스 개별로 바뀔 때 전체동의 상태도 갱신
    subAgrees.forEach(function (checkbox) {
      checkbox.addEventListener("change", function () {
        const allChecked = Array.from(subAgrees).every(cb => cb.checked);
        agreeAll.checked = allChecked;
      });
    });
  });

  document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".submit-btn").addEventListener("click", () => {
      window.location.href = "login.html";
    });
  });

  document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".back-btn").addEventListener("click", () => {
      window.location.href = "index.html";
    });
  });