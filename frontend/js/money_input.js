// 추후 버튼 기능 연결을 위한 스크립트 예시
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".recommend-btn").addEventListener("click", () => {
      window.location.href = "recomm_main.html";
    });
  });
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".nav-button").addEventListener("click", () => {
      window.location.href = "analysis.html";
    });
  });
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".my-button").addEventListener("click", () => {
      window.location.href = "mypage.html";
    });
  });
  
  document.addEventListener("DOMContentLoaded", function () {
    const seoulDistricts = [
      "강남구", "강동구", "강북구", "강서구", "관악구",
      "광진구", "구로구", "금천구", "노원구", "도봉구",
      "동대문구", "동작구", "마포구", "서대문구", "서초구",
      "성동구", "성북구", "송파구", "양천구", "영등포구",
      "용산구", "은평구", "종로구", "중구", "중랑구"
    ];
  
    const container = document.getElementById("region-container");
    let selectedLabel = null; // 현재 선택된 라벨 추적
  
    seoulDistricts.forEach(name => {
      const label = document.createElement("label");
      label.className = "region-option";
  
      // checkbox 대신 radio 사용 (단일 선택)
      const radio = document.createElement("input");
      radio.type = "radio";
      radio.name = "region"; // 모든 radio 버튼에 같은 name 부여
      radio.value = name;
  
      label.appendChild(radio);
      label.append(` ${name}`);
  
      container.appendChild(label);
  
      // 선택 시 토글 스타일
      radio.addEventListener("change", () => {
        // 이전에 선택된 항목이 있으면 selected 클래스 제거
        if (selectedLabel && selectedLabel !== label) {
          selectedLabel.classList.remove("selected");
        }
        
        // 현재 선택된 항목에 selected 클래스 추가
        label.classList.add("selected");
        selectedLabel = label;
      });
    });
    
    // 페이지 로드 시 스크롤 위치 맨 위로 (처음 4개 항목 보이도록)
    container.scrollTop = 0;
  });