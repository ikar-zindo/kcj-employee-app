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
  customer_id INT AUTO_INCREMENT PRIMARY KEY,
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
  cart_id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_product` (
  cart_product_id INT AUTO_INCREMENT PRIMARY KEY,
  cart_id INT,
  product_id INT,
  quantity INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  order_id INT AUTO_INCREMENT PRIMARY KEY,
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
  order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
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
  product_id INT AUTO_INCREMENT PRIMARY KEY,
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
  restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
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
  review_id INT AUTO_INCREMENT PRIMARY KEY,
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

ALTER TABLE `cart-product`
  ADD KEY `product_id` (`product_id`);
  
--
-- Indexes for table `order`
--

ALTER TABLE `order`
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `restaurant_id` (`restaurant_id`);










 
--
-- Indexes for table `product`
--

ALTER TABLE `product`
  ADD KEY `category_id` (`category_id`),
  ADD KEY `supplier_id` (`supplier_id`);

--
-- Indexes for table `cart_product`
--

ALTER TABLE `cart_product`
  ADD KEY `cart_id` (`cart_id`),
  ADD KEY `product_id` (`product_id`);



-- --------------------------------------------------------
