CREATE TABLE regions (
        region_id INT PRIMARY KEY AUTO_INCREMENT,
        gu_name VARCHAR(50) NOT NULL,
        dong_name VARCHAR(50) NOT NULL,
        UNIQUE (gu_name, dong_name)
);

CREATE TABLE apartment_rent (
        rent_id INT AUTO_INCREMENT PRIMARY KEY,
        region_id INT,
        rent_price INT,
        FOREIGN KEY (region_id) REFERENCES regions(region_id)
);

CREATE TABLE buildings (
	building_id INT PRIMARY KEY AUTO_INCREMENT,
        building_name VARCHAR(100) NOT NULL,
        region_id INT,
        FOREIGN KEY (region_id) REFERENCES regions(region_id)
);

CREATE TABLE rooms (
        room_id INT AUTO_INCREMENT PRIMARY KEY,
        building_id INT,
        deposit INT,
        monthly_rent INT,
        floor INT,
        room_num INT,
        FOREIGN KEY (building_id) REFERENCES buildings(building_id),
        UNIQUE (building_id, room_num)
);
    
CREATE TABLE users (
        user_id INT AUTO_INCREMENT PRIMARY KEY,
        login_id VARCHAR(50),
        password VARCHAR(50),
        email VARCHAR(100),
        user_name VARCHAR(50)
);
    
CREATE TABLE user_preference (
        pref_id INT AUTO_INCREMENT PRIMARY KEY,
        user_id INT,
        region_id INT,
        deposit_min INT,
        deposit_max INT,
        rent_min INT,
        rent_max INT,
        created_at DATE,
        FOREIGN KEY (user_id) REFERENCES users (user_id),
        FOREIGN KEY (region_id) REFERENCES regions(region_id)
);