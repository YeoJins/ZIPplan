// 추후 버튼 기능 연결을 위한 스크립트 예시
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".recommend-btn").addEventListener("click", () => {
      window.location.href = "recomm_main.html";
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
  
    seoulDistricts.forEach(name => {
      const label = document.createElement("label");
      label.className = "region-option";
  
      const checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      checkbox.value = name;
  
      label.appendChild(checkbox);
      label.append(` ${name}`);
  
      container.appendChild(label);
  
      // 선택 시 토글 스타일
      checkbox.addEventListener("change", () => {
        label.classList.toggle("selected", checkbox.checked);
      });
    });
  });
  