SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+01:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE DATABASE IF NOT EXISTS `k-curry-jib` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `k-curry-jib`;

--
-- Database: `k-curry-jib`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  phone_number VARCHAR(20),
  address VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_blocked BOOL DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  cart_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id INT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_product` (
  cart_product_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cart_id INT,
  product_id INT,
  quantity INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id INT,
  restaurant_id INT,
  order_date DATETIME,
  delivery_address VARCHAR(255),
  total_amount DECIMAL(10, 2),
  order_status VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  order_detail_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  order_id INT,
  product_id INT,
  quantity INT,
  total DECIMAL(10, 2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `product`
--


CREATE TABLE `product` (
  product_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(123),
  description TEXT,
  price DECIMAL(10, 2),
  restaurant_id INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_available BOOL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  restaurant_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  address VARCHAR(255),
  phone_number VARCHAR(20),
  opening_hours VARCHAR(255),
  cuisine_type VARCHAR(100),
  description TEXT,
  social_media_links VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  review_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  restaurant_id INT,
  customer_id INT,
  rating DECIMAL(3, 2),
  comment TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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

ALTER TABLE `order_detail`
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
  ADD CONSTRAINT `order_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`);
  
--
-- Constraints for table `order_details`
--

ALTER TABLE `order_detail` 
	ADD CONSTRAINT `order_detai_fk_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
	ADD CONSTRAINT `order_detai_fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

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
	ADD CONSTRAINT `review_fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;