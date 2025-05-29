document.addEventListener("DOMContentLoaded", function () {
  const agreeAll = document.getElementById("agree-all");
  const subAgrees = document.querySelectorAll(".sub-agree");
  const submitBtn = document.querySelector(".submit-btn");
  const backBtn = document.querySelector(".back-btn");

  // 전체 동의 체크박스 → 하위 항목 전체 선택
  agreeAll.addEventListener("change", function () {
    subAgrees.forEach(function (checkbox) {
      checkbox.checked = agreeAll.checked;
    });
  });

  // 하위 체크박스 상태 → 전체 동의 체크박스 업데이트
  subAgrees.forEach(function (checkbox) {
    checkbox.addEventListener("change", function () {
      const allChecked = Array.from(subAgrees).every((cb) => cb.checked);
      agreeAll.checked = allChecked;
    });
  });

  // 회원가입 버튼 클릭 시 서버 요청
  submitBtn.addEventListener("click", () => {
    const loginId = document.querySelectorAll("input")[1].value; // 아이디
    const password = document.querySelectorAll("input[type='password']")[0]
      .value;
    const passwordConfirm = document.querySelectorAll(
      "input[type='password']"
    )[1].value;
    const email = document.querySelector("input[type='email']").value;
    const userName = document.querySelector("input[type='text']").value;

    // 필수 입력 체크
    if (!loginId || !password || !passwordConfirm || !email || !userName) {
      alert("모든 항목을 입력해주세요.");
      return;
    }

    // 비밀번호 확인
    if (password !== passwordConfirm) {
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }

    // 필수 약관 3개 체크 여부 확인
    const requiredChecks = document.querySelectorAll(".sub-agree:checked");
    if (requiredChecks.length < 3) {
      alert("필수 약관에 모두 동의해야 회원가입이 가능합니다.");
      return;
    }

    // 서버로 회원가입 요청 보내기
    fetch("http://localhost:8080/api/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ loginId, password, email, userName }),
    })
      .then((res) => res.json())
      .then((result) => {
        if (result) {
          alert("회원가입 성공!");
          window.location.href = "login.html";
        } else {
          alert("회원가입 실패! 아이디 중복이거나 서버 오류입니다.");
        }
      })
      .catch((err) => {
        console.error("에러 발생:", err);
        alert("서버와의 연결에 실패했습니다.");
      });
  });

  // 뒤로가기 버튼 (index.html로)
  backBtn.addEventListener("click", () => {
    window.location.href = "index.html";
  });
});
