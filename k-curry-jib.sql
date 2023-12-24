SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+01:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE DATABASE IF NOT EXISTS `k-curry-jib` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `k-curry-jib`;

--
-- Database: `k-curry-jib`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  customer_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(60),
  last_name VARCHAR(60),
  email VARCHAR(60),
  password VARCHAR(120),
  phone_number VARCHAR(20),
  address VARCHAR(120),
  postal_code VARCHAR(5),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_blocked BOOL DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  cart_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_product` (
  cart_product_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cart_id BIGINT,
  product_id BIGINT,
  quantity INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  order_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT,
  restaurant_id BIGINT,
  employee_id BIGINT,
  order_date DATETIME,
  delivery_address VARCHAR(60),
  total_amount DECIMAL(8, 2),
  order_status VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_product` (
  order_product_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT,
  product_id BIGINT,
  quantity INT,
  total DECIMAL(8, 2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  product_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60),
  description TEXT,
  price DECIMAL(8, 2),
  restaurant_id BIGINT,
  image_url VARCHAR(200),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_available BOOL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  restaurant_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60),
  address VARCHAR(120),
  phone_number VARCHAR(20),
  opening_hours VARCHAR(20),
  cuisine_type VARCHAR(20),
  description TEXT,
  social_media_links VARCHAR(200),
  is_open BOOL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  review_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  restaurant_id BIGINT,
  customer_id BIGINT,
  rating DECIMAL(5, 2),
  comment TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  employee_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  email VARCHAR(120),
  nickname VARCHAR(60),
  password VARCHAR(120),
  phone_number VARCHAR(20),
  role ENUM('ROLE_USER', 'ROLE_MANAGER', 'ROLE_ADMIN', 'ROLE_DEALER', 'ROLE_DRIVER'),
  restaurant_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_active BOOL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- --------------------------------------------------------

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--

ALTER TABLE `cart`
  ADD KEY `customer_id` (`customer_id`);
  
--
-- Indexes for table `cart-product`
--

ALTER TABLE `cart_product`
  ADD KEY `product_id` (`product_id`);
  
--
-- Indexes for table `order`
--

ALTER TABLE `order`
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `restaurant_id` (`restaurant_id`);

--
-- Indexes for table `order_detail`
--

ALTER TABLE `order_product`
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--

ALTER TABLE `product`
  ADD KEY `restaurant_id` (`restaurant_id`);

--
-- Indexes for table `review`
--

ALTER TABLE `review`
  ADD KEY `restaurant_id` (`restaurant_id`),
  ADD KEY `customer_id` (`customer_id`);
  
--
-- Indexes for table `employee`
--

ALTER TABLE `employee`
  ADD KEY `employee_id` (`employee_id`);

-- --------------------------------------------------------

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--

ALTER TABLE `cart`
  ADD CONSTRAINT `cart_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);
  
--
-- Constraints for table `cart_product`
--

ALTER TABLE `cart_product` 
	ADD CONSTRAINT `cart_product_fk_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
	ADD CONSTRAINT `cart_product_fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `order`
--

ALTER TABLE `order`
  ADD CONSTRAINT `order_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `order_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
  ADD CONSTRAINT `order_fk_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);
  
--
-- Constraints for table `order_details`
--

ALTER TABLE `order_product` 
	ADD CONSTRAINT `order_product_fk_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
	ADD CONSTRAINT `order_product_fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `product`
--

ALTER TABLE `product`
	ADD CONSTRAINT `product_fk_category` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
	ADD CONSTRAINT `product_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`);
	
--
-- Constraints for table `review`
--

ALTER TABLE `review`
	ADD CONSTRAINT `review_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
	ADD CONSTRAINT `review_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `employee`
--

ALTER TABLE `employee`
    ADD CONSTRAINT `employee_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`);
	
-- --------------------------------------------------------

--
-- Dumping data for tables
--

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`name`, `address`, `phone_number`, `opening_hours`, `cuisine_type`, `description`, `social_media_links`) VALUES
    ('K-Curry Jib', 'Alexanderplaz 1, 10178', '+49123456789', '12:00 - 22:00', 'Asian food', '', 'social-media-link.com');

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`first_name`, `last_name`, `email`, `password`, `phone_number`, `address`, `postal_code`) VALUES
    ('Maria', 'Anders',	'maria@mailcom', '1qaz2wsx', '+49123456789', 'Obere Str. 57', '12209'),
    ('Ana', 'Trujillo',	'ana@mailcom', '1qaz2wsx', '+49123456789', 'Minerstrasse 33', '10115'),
    ('Antonio', 'Moreno', 'antonio@mailcom', '1qaz2wsx', '+49123456789', 'Alexanderplatz 3', '10178'),
    ('Thomas', 'Hardy',	'thomas@mailcom', '1qaz2wsx', '+49123456789', 'Friedrichstrasse 123', '10117'),
    ('Christina', 'Berglund', 'christina@mailcom', '1qaz2wsx', '+49123456789', 'Karl-Liebknecht-Strasse 29', '10178'),
    ('Hanna', 'Moos', 'hanna@mailcom', '1qaz2wsx', '+49123456789', 'Potsdamer Platz 1', '10785'),
    ('Frederique', 'Citeaux', 'frederique@mailcom', '1qaz2wsx', '+49123456789', 'Stralauer Strasse 34', '10243'),
    ('Martin', 'Sommer', 'martin@mailcom', '1qaz2wsx', '+49123456789', 'Lichtenberger Strasse 11', '10179'),
    ('Laurence', 'Lebihans', 'laurence@mailcom', '1qaz2wsx', '+49123456789', 'Charlottenstrasse 22', '10117'),
    ('Elizabeth', 'Lincoln', 'elizabeth@mailcom', '1qaz2wsx', '+49123456789', 'Pariser Platz 7', '10117');

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`customer_id`) VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10);

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`last_name`, `first_name`,  `email`, `nickname`, `password`, `phone_number`, `role`, `restaurant_id`) VALUES
    ('Davolio', 'Nancy', 'davolio@mail.com', 'nancy', 'qwerty123', '+49123456789', 'ROLE_USER', 1),
    ('Fuller', 'Andrew', 'fuller@mail.com', 'andrew', 'qwerty123', '+49123456789', 'ROLE_USER', 1),
    ('Leverling', 'Janet', 'leverling@mail.com', 'janet', 'qwerty123', '+49123456789', 'ROLE_USER', 1),
    ('Peacock', 'Margaret', 'peacock@mail.com', 'margaret', 'qwerty123', '+49123456789', 'ROLE_USER', 1),
    ('Buchanan', 'Steven', 'buchanan@mail.com', 'steven', 'qwerty123', '+49123456789', 'ROLE_USER', 1),
    ('Suyama', 'Michael', 'suyama@mail.com', 'michael', 'qwerty123', '+49123456789', 'ROLE_MANAGER', 1),
    ('King', 'Robert', 'king@mail.com', 'robert', 'qwerty123', '+49123456789', 'ROLE_MANAGER', 1),
    ('Callahan', 'Laura', 'callahan@mail.com', 'laura', 'qwerty123', '+49123456789', 'ROLE_ADMIN', 1),
    ('Dodsworth', 'Anne', 'dodsworth@mail.com', 'anne', 'qwerty123', '+49123456789', 'ROLE_DRIVER', 1),
    ('West', 'Adam', 'west@mail.com', 'adam', 'qwerty123', '+49123456789', 'ROLE_DRIVER', 1);

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`name`, `description`,  `price`, `restaurant_id`) VALUES
    ('Chicken Curry', 'Fried chicken with vegetables and rice in medium spicy curry sauce', 14, 1),
    ('Bibimbap', 'Beef with rice, vegetables, seaweed, fried egg and chilli paste', 12, 1),
    ('Samgyupsal', 'Bacon for grill, lettuce, garlic, soy bean Samjang paste and leek salad', 40, 1),
    ('KimChi', 'Korean fermented dish of seasoned vegetables, known for its tangy, spicy taste', 4, 1),
    ('KimChi Zige', 'Spicy soup with kimchi, bacon, tofu & vegetables', 13, 1),
    ('Ozingo Kampungi', 'Fried battered squids in spicy-and-sweet sauce and vegetables', 18, 1),
    ('Ramen', 'Soup with noodles, beef or pork, egg, katsuobushi, vegetables and leek', 10, 1),
    ('Haemul Ramen', 'Spicy noodle soup with seafood, vegetables and egg', 7, 1),
    ('Miso  Shiru', 'Soybean soup with tofu, seaweed', 5, 1),
    ('Haemul Bokkum', 'Fried seawood with vegetables', 15, 1),
    ('Ori Teriyaki', 'Grilled duck with vegetables in sweet teriyaki sauce', 18, 1),
    ('Kampungi', 'Medium spicy chicken breast with vegetables', 10, 1),
    ('Chiken Teriyaki', 'Fried chicken leg with grilled vegetables in sweet teriyaki sauce', 8, 1),
    ('Yaki Tori', 'Chicken shashliks (2 pieces) in sweet sauce, fries', 6, 1),
    ('Yaki Gyoza', 'Fried dumplings with vegetable filling', 8, 1),
    ('Ebi Tempura', 'Fried shrimps coated in tempura, fries', 16, 1),
    ('Dubu Yache Bokkum', 'Fried tofu with vegetables in soya sauce', 9, 1),
    ('Yukhoe', 'Beef tartar, garlic, onion, sesame oil', 20, 1),
    ('Bulgogi Zungshik', 'Beef & vehetables in sweet sauce on the hot plate, rice', 13, 1),
    ('Osam Bokum', 'Fried squids, pork and vegetables in spicy gochujang sauce', 28, 1);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;