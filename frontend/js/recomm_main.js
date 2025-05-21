document.addEventListener("DOMContentLoaded", function () {
  const viewAllBtn = document.querySelector(".view-all");
  const viewAllReturnBtn = document.querySelector(".view-all-return");
  const navButton = document.querySelector(".nav-button");
  const myButton = document.querySelector(".my-button");

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
      window.location.href = "analysis.html";
    });
  }

  if (myButton) {
    myButton.addEventListener("click", () => {
      window.location.href = "mypage.html";
    });
  }
});
