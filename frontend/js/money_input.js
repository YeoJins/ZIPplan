document.addEventListener("DOMContentLoaded", function () {
  // 추천, 분석, 마이페이지 버튼 이동
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

  const seoulDistricts = [
    "강남구", "강동구", "강북구", "강서구", "관악구",
    "광진구", "구로구", "금천구", "노원구", "도봉구",
    "동대문구", "동작구", "마포구", "서대문구", "서초구",
    "성동구", "성북구", "송파구", "양천구", "영등포구",
    "용산구", "은평구", "종로구", "중구", "중랑구"
  ];

  const container = document.getElementById("region-container");
  let selectedLabel = null;

  // ✅ dummy 라디오 버튼 생성 (선택 해제를 위해)
  const dummyRadio = document.createElement("input");
  dummyRadio.type = "radio";
  dummyRadio.name = "region";
  dummyRadio.id = "dummy-radio";
  dummyRadio.style.display = "none";
  dummyRadio.checked = true; // 기본 선택
  container.appendChild(dummyRadio);

  // 라디오 버튼 생성
  seoulDistricts.forEach(name => {
    const label = document.createElement("label");
    label.className = "region-option";

    const radio = document.createElement("input");
    radio.type = "radio";
    radio.name = "region";
    radio.value = name;

    label.appendChild(radio);
    label.append(` ${name}`);

    container.appendChild(label);

    // 선택 시 스타일 토글
    radio.addEventListener("change", () => {
      if (selectedLabel && selectedLabel !== label) {
        selectedLabel.classList.remove("selected");
      }
      label.classList.add("selected");
      selectedLabel = label;
    });
  });

  // 스크롤 초기화
  container.scrollTop = 0;

    // 수정 버튼 기능 추가
  const editButton = document.querySelector(".edit");
  editButton.addEventListener("click", () => {
    const inputs = document.querySelectorAll('.range-group input');
    inputs.forEach(input => input.disabled = false); // 입력 가능하게
  });

  // 저장 버튼 기능
  document.querySelector(".save").addEventListener("click", () => {
    const inputs = document.querySelectorAll('.range-group input');
    inputs.forEach(input => input.disabled = true); // 입력 불가능하게
  });

  // 삭제 버튼 기능
  const deleteButton = document.querySelector(".delete");
  deleteButton.addEventListener("click", () => {
    // 월세/보증금 input 초기화
    const inputs = document.querySelectorAll('.range-group input');
    inputs.forEach(input => input.value = '');

    // 라디오 버튼 선택 해제 (dummy 선택)
    const dummyRadio = document.getElementById('dummy-radio');
    if (dummyRadio) {
      dummyRadio.checked = true;
    }

    // 선택된 라벨 하이라이트 해제
    if (selectedLabel) {
      selectedLabel.classList.remove("selected");
      selectedLabel = null;
    }
  });
});
