document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector(".login-form");

  if (!form) {
    console.error("폼을 찾을 수 없습니다.");
    return;
  }

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const loginId = document.querySelector("#loginId").value;
    const password = document.querySelector("#password").value;

    if (!loginId || !password) {
      alert("아이디와 비밀번호를 모두 입력해주세요.");
      return;
    }

    fetch("http://localhost:8080/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ loginId, password }),
    })
      .then((res) => res.json())
      .then((data) => {
        if (data && !data.error) {
          localStorage.setItem("userName", data.userName);
          localStorage.setItem("loginId", data.loginId);
          alert("✅ 로그인 성공!");
          window.location.href = "mypage.html";
        } else {
          alert("❌ 로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
        }
      })
      .catch((err) => {
        console.error("에러 발생:", err);
        alert("서버 연결 오류");
      });
  });
});
