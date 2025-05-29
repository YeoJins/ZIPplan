document.addEventListener("DOMContentLoaded", function () {
  const userNameEl = document.getElementById("user-name");
  const userName = localStorage.getItem("userName");

  if (userNameEl && userName) {
    userNameEl.textContent = `${userName}님 반가워요!`;
  }
  // 페이지 이동 버튼
  document.querySelector(".recommend-btn").addEventListener("click", () => {
    window.location.href = "recomm_main.html";
  });
  document.querySelector(".nav-button").addEventListener("click", () => {
    window.location.href = "money_input.html";
  });
  document.querySelector(".my-button").addEventListener("click", () => {
    window.location.href = "mypage.html";
  });
  document.querySelector(".left-button").addEventListener("click", () => {
    window.location.href = "analysis.html";
  });

  // 지역 목록 및 ID 매핑
  const seoulDistricts = [
    "강남구",
    "강동구",
    "강북구",
    "강서구",
    "관악구",
    "광진구",
    "구로구",
    "금천구",
    "노원구",
    "도봉구",
    "동대문구",
    "동작구",
    "마포구",
    "서대문구",
    "서초구",
    "성동구",
    "성북구",
    "송파구",
    "양천구",
    "영등포구",
    "용산구",
    "은평구",
    "종로구",
    "중구",
    "중랑구",
  ];

  const regionMap = {
    강남구: 1,
    강동구: 2,
    강북구: 3,
    강서구: 4,
    관악구: 5,
    광진구: 6,
    구로구: 7,
    금천구: 8,
    노원구: 9,
    도봉구: 10,
    동대문구: 11,
    동작구: 12,
    마포구: 13,
    서대문구: 14,
    서초구: 15,
    성동구: 16,
    성북구: 17,
    송파구: 18,
    양천구: 19,
    영등포구: 20,
    용산구: 21,
    은평구: 22,
    종로구: 23,
    중구: 24,
    중랑구: 25,
  };

  const container = document.getElementById("region-container");
  let selectedLabel = null;
  const saveBtn = document.querySelector(".save");
  const deleteBtn = document.querySelector(".delete");
  const updateBtn = document.querySelector(".update");

  let isEditMode = false;

  // dummy radio for deselection
  const dummyRadio = document.createElement("input");
  dummyRadio.type = "radio";
  dummyRadio.name = "region";
  dummyRadio.id = "dummy-radio";
  dummyRadio.style.display = "none";
  dummyRadio.checked = true;
  container.appendChild(dummyRadio);

  // 지역 선택 라디오 생성
  seoulDistricts.forEach((name) => {
    const label = document.createElement("label");
    label.className = "region-option";

    const radio = document.createElement("input");
    radio.type = "radio";
    radio.name = "region";
    radio.value = name;

    label.appendChild(radio);
    label.append(` ${name}`);
    container.appendChild(label);

    radio.addEventListener("change", () => {
      if (selectedLabel && selectedLabel !== label) {
        selectedLabel.classList.remove("selected");
      }
      label.classList.add("selected");
      selectedLabel = label;
    });
  });

  container.scrollTop = 0;

  // 수정 버튼
  updateBtn.addEventListener("click", () => {
    document
      .querySelectorAll(".range-group input")
      .forEach((input) => (input.disabled = false));
    isEditMode = true;
  });

  // 저장 버튼 (등록 & 수정)
  saveBtn.addEventListener("click", () => {
    document.querySelectorAll(".range-group input").forEach((input) => {
      input.disabled = true;
    });

    const selectedRadio = document.querySelector(
      'input[name="region"]:checked'
    );
    const selectedRegion = selectedRadio?.value;
    const regionId = regionMap[selectedRegion];

    const [rentMinEl, rentMaxEl] = document.querySelectorAll(
      ".section:nth-of-type(2) .range-group input"
    );
    const [depositMinEl, depositMaxEl] = document.querySelectorAll(
      ".section:nth-of-type(3) .range-group input"
    );

    const rentMin = parseInt(rentMinEl.value.replaceAll(",", "")) || 0;
    const rentMax = parseInt(rentMaxEl.value.replaceAll(",", "")) || 0;
    const depositMin = parseInt(depositMinEl.value.replaceAll(",", "")) || 0;
    const depositMax = parseInt(depositMaxEl.value.replaceAll(",", "")) || 0;

    const userId = Number(localStorage.getItem("userId"));
    if (!userId) {
      alert("로그인이 필요합니다.");
      window.location.href = "login.html";
      return;
    }
    const data = {
      userId,
      regionId,
      depositMin,
      depositMax,
      rentMin,
      rentMax,
    };

    fetch("http://localhost:8080/api/preference", {
      method: isEditMode ? "PUT" : "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    })
      .then((res) => res.json())
      .then((success) => {
        if (success) {
          alert(isEditMode ? "수정 완료!" : "등록 완료!");
        } else {
          alert("실패했습니다.");
        }
      });
  });

  // 삭제 버튼
  deleteBtn.addEventListener("click", () => {
    const inputs = document.querySelectorAll(".range-group input");
    inputs.forEach((input) => (input.value = ""));

    const dummyRadio = document.getElementById("dummy-radio");
    if (dummyRadio) dummyRadio.checked = true;

    if (selectedLabel) {
      selectedLabel.classList.remove("selected");
      selectedLabel = null;
    }

    const userId = Number(localStorage.getItem("userId"));

    fetch("http://localhost:8080/api/preference", {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ userId }),
    })
      .then((res) => res.json())
      .then((success) => {
        if (success) alert("삭제 완료!");
        else alert("삭제 실패!");
      });
  });
});
