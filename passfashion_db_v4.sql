-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 21, 2025 lúc 08:16 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

use `passfashion_db`;
show tables;
SELECT * FROM address_order;
SELECT * FROM addresses;
SELECT * FROM card_order;
SELECT * FROM categories;
SELECT * FROM comments;
SELECT * FROM credit_card;
SELECT * FROM employees;
SELECT * FROM images;
SELECT * FROM orders;
SELECT * FROM password_reset_tokens;
SELECT * FROM permissions;
-- SELECT * FROM product_details;
SELECT * FROM product_order;
SELECT * FROM products;
SELECT * FROM user_permissions;
SELECT * FROM users;
SELECT * FROM voucher_order;
SELECT * FROM vouchers;
SELECT * FROM wishlists;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS address_order;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS card_order;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS credit_card;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS image_products;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS password_reset_tokens;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS product_details;
DROP TABLE IF EXISTS product_order;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS user_permissions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS voucher_order;
DROP TABLE IF EXISTS vouchers;
DROP TABLE IF EXISTS wishlists;
SET FOREIGN_KEY_CHECKS = 1;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `passfashion_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `addresses`
--

CREATE TABLE `addresses` (
  `id` bigint(20) NOT NULL,
  `detail` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `is_default` int(11) NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT 0,
  `phone` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `ward` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `address_order`
--

CREATE TABLE `address_order` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `card_order`
--

CREATE TABLE `card_order` (
  `id` bigint(20) NOT NULL,
  `card_infor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  `link` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `is_deleted`, `link`, `title`) VALUES
(1, 0, 'quan-ao', 'Quần áo'),
(2, 0, 'giay-dep', 'Giấy dép'),
(3, 0, 'tui-xach', 'Túi xách'),
(4, 0, 'dong-ho', 'Đồng hồ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `is_deleted` int(11) NOT NULL DEFAULT 0,
  `level` int(11) NOT NULL DEFAULT 0,
  `parent_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `comments`
--

INSERT INTO `comments` (`id`, `content`, `created_at`, `is_deleted`, `level`, `parent_id`, `product_id`, `user_id`) VALUES
(1, 'Hello', '2025-06-22 01:00:25', 0, 0, NULL, 9, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `credit_card`
--

CREATE TABLE `credit_card` (
  `id` bigint(20) NOT NULL,
  `ccv` varchar(255) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employees`
--

CREATE TABLE `employees` (
  `department` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `images`
--

CREATE TABLE `images` (
  `id` bigint(20) NOT NULL,
  `alt` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `images`
--

INSERT INTO `images` (`id`, `alt`, `url`, `product_id`) VALUES
(1, 'default', 'uploads/default.png', 1),
(2, 'tui', 'uploads/bag.png', 1),
(3, 'dong ho ', 'uploads/category-dong-ho-1.png', 4),
(4, 'dong ho ', 'uploads/category-dong-ho-2.png', 4),
(5, 'giay dep ', 'uploads/category-giay-dep-1.jpg', 5),
(6, 'giay dep', 'uploads/category-giay-dep-2.png', 6),
(7, 'tui', 'uploads/category-tui-xach-3.png', 2),
(8, 'tui', 'uploads/category-tui-xach-4.jpg', 3),
(9, 'ao', 'uploads/clothes.png', 11),
(10, 'ao', 'uploads/category-quan-ao-4.jpg', 10),
(11, 'quan', 'uploads/category-quan-ao-2.png', 9),
(12, 'giay dep', 'uploads/category-giay-dep-2.png', 7),
(13, 'giay dep', 'uploads/category-giay-dep-1.jpg', 8),
(14, 'quan ao', 'uploads/clothes.png', 11),
(15, 'quan ao', 'uploads/category-quan-ao2.png', 12),
(16, 'quan ao', 'uploads/clothes.png', 13),
(17, 'dong ho', 'uploads/accessory.jpg', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `address_order_id` bigint(20) NOT NULL,
  `card_order_id` bigint(20) NOT NULL,
  `product_order_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `voucher_order_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `password_reset_tokens`
--

CREATE TABLE `password_reset_tokens` (
  `id` bigint(20) NOT NULL,
  `expiry_date` datetime(6) NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permissions`
--

CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `condition` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `description` text DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `is_sold` tinyint(1) NOT NULL DEFAULT 0,
  `material` varchar(255) DEFAULT NULL,
  `negotiable` bit(1) DEFAULT NULL,
  `pickup_address` varchar(255) DEFAULT NULL,
  `price` double NOT NULL DEFAULT 0,
  `qty` int(11) DEFAULT 1,
  `sale_price` double DEFAULT NULL,
  `terms_accepted` bit(1) DEFAULT NULL,
  `unsigned_name` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `name`, `brand`, `condition`, `created_at`, `description`, `is_deleted`, `is_sold`, `material`, `negotiable`, `pickup_address`, `price`, `qty`, `sale_price`, `terms_accepted`, `unsigned_name`, `category_id`, `user_id`) VALUES
(1, 'Túi Gucci da cá sấu cho nữ', 'Gucci', NULL, '2025-06-21 22:24:35', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'quận 1 - hcm', 4000000, 1, 0, b'0', 'tui gucci da ca sau cho nu', 3, 1),
(2, 'Túi channel da heo', 'Channel', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'đống đa - hà nội', 6000000, 1, 0, b'0', 'tui channel da heo', 3, 1),
(3, 'Ví vans sọc caro', 'Vans', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'quận 1 - hcm', 300000, 1, 0, b'0', 'vi van soc caro', 3, 1),
(4, 'Đồng hồ Timex', 'Timex', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 900000, 1, 0, b'0', 'dong ho timex', 4, 1),
(5, 'Giày Adidas size 39', 'Adidas', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 900000, 1, 0, b'0', 'giay adidas size 39', 2, 1),
(6, 'Giày Nike nữ size 36', 'Nike', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 700000, 1, 0, b'0', 'giay nike nu size 36', 2, 1),
(7, 'Dép Adidas size 39', 'Adidas', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 300000, 1, 0, b'0', 'dep adidas size 39', 2, 1),
(8, 'Dép Adidas trắng đen 39', 'Adidas', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 250000, 1, 0, b'0', 'dep adidas trang den 39', 2, 1),
(9, 'Áo khoác tuyển đức', 'Adidas', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 350000, 1, 0, b'0', 'ao khoac tuyen duc', 1, 1),
(10, 'Áo samsung s25', 'Adidas', NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 50000, 1, 0, b'0', 'ao samsung s25', 1, 1),
(11, 'Quần jogger size 39', NULL, NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 150000, 1, 0, b'0', 'quan jogger size 39', 1, 1),
(12, 'Đầm đen size m', NULL, NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 300000, 1, 0, b'0', 'dam den size m', 1, 1),
(13, 'Váy trắng size m', NULL, NULL, '2025-06-21 22:51:17', 'Được tặng không dùng nên pass lại', 0, 0, NULL, b'0', 'binh thanh - hcm', 320000, 1, 0, b'0', 'vay trang size m', 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_order`
--

CREATE TABLE `product_order` (
  `id` bigint(20) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) NOT NULL,
  `rating` double NOT NULL DEFAULT 0,
  `role` enum('ADMIN','USER') NOT NULL,
  `sold_order_qty` int(11) NOT NULL DEFAULT 0,
  `total_review` int(11) NOT NULL DEFAULT 0,
  `avatar` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `birthday`, `email`, `is_deleted`, `name`, `phone`, `pwd`, `rating`, `role`, `sold_order_qty`, `total_review`, `avatar`) VALUES
(1, '2003-04-04', 'tonhat@gmail.com', 0, 'To nhat', '01253051340', '$2a$10$BfsuWtMFiwIVvgTgPhXC9OHja8arq9sKu9z9HSZfod4HUf8eUggXW', 4.9, 'USER', 4, 4, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_permissions`
--

CREATE TABLE `user_permissions` (
  `user_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vouchers`
--

CREATE TABLE `vouchers` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount` int(11) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `min_order_value` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `voucher_order`
--

CREATE TABLE `voucher_order` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `wishlists`
--

CREATE TABLE `wishlists` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `wishlists`
--

INSERT INTO `wishlists` (`id`, `created_at`, `product_id`, `user_id`) VALUES
(1, '2025-06-22 01:01:17.000000', 9, 1),
(2, '2025-06-22 01:10:13.000000', 13, 1),
(3, '2025-06-22 01:10:40.000000', 11, 1),
(4, '2025-06-22 01:11:23.000000', 10, 1),
(5, '2025-06-22 01:12:29.000000', 5, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1fa36y2oqhao3wgg2rw1pi459` (`user_id`);

--
-- Chỉ mục cho bảng `address_order`
--
ALTER TABLE `address_order`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `card_order`
--
ALTER TABLE `card_order`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK4ej3v7j8udemjeixnppvbtfg3` (`link`);

--
-- Chỉ mục cho bảng `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlri30okf66phtcgbe5pok7cc0` (`parent_id`),
  ADD KEY `FK6uv0qku8gsu6x1r2jkrtqwjtn` (`product_id`),
  ADD KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`);

--
-- Chỉ mục cho bảng `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKes0kii3rdngv6p26vsih412jy` (`user_id`);

--
-- Chỉ mục cho bảng `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKghwsjbjo7mg3iufxruvq6iu3q` (`product_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKh2q090p3e4k4u3ockqaica2k8` (`address_order_id`),
  ADD UNIQUE KEY `UKt9vw6rnc7iqya1cgvdluj7a9k` (`card_order_id`),
  ADD UNIQUE KEY `UKp8qj1hdaoknsgrt0snwqmspxf` (`product_order_id`),
  ADD UNIQUE KEY `UKkkl219ggamvoj5mpah2r7okxy` (`voucher_order_id`),
  ADD KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`);

--
-- Chỉ mục cho bảng `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKla2ts67g4oh2sreayswhox1i6` (`user_id`);

--
-- Chỉ mục cho bảng `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKpnvtwliis6p05pn6i3ndjrqt2` (`name`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  ADD KEY `FKdb050tk37qryv15hd932626th` (`user_id`);

--
-- Chỉ mục cho bảng `product_order`
--
ALTER TABLE `product_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo7dl8saqqnhuc1e5pkoabjr1a` (`category_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user_permissions`
--
ALTER TABLE `user_permissions`
  ADD KEY `FKq4qlrabt4s0etm9tfkoqfuib1` (`permission_id`),
  ADD KEY `FKe462ejfjrceotgioginphsva8` (`user_id`);

--
-- Chỉ mục cho bảng `vouchers`
--
ALTER TABLE `vouchers`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `voucher_order`
--
ALTER TABLE `voucher_order`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `wishlists`
--
ALTER TABLE `wishlists`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKl7ao98u2bm8nijc1rv4jobcrx` (`product_id`),
  ADD KEY `FK330pyw2el06fn5g28ypyljt16` (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `address_order`
--
ALTER TABLE `address_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `card_order`
--
ALTER TABLE `card_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `credit_card`
--
ALTER TABLE `credit_card`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `images`
--
ALTER TABLE `images`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `permissions`
--
ALTER TABLE `permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `product_order`
--
ALTER TABLE `product_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `vouchers`
--
ALTER TABLE `vouchers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `voucher_order`
--
ALTER TABLE `voucher_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `wishlists`
--
ALTER TABLE `wishlists`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `FK1fa36y2oqhao3wgg2rw1pi459` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK6uv0qku8gsu6x1r2jkrtqwjtn` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKlri30okf66phtcgbe5pok7cc0` FOREIGN KEY (`parent_id`) REFERENCES `comments` (`id`);

--
-- Các ràng buộc cho bảng `credit_card`
--
ALTER TABLE `credit_card`
  ADD CONSTRAINT `FKes0kii3rdngv6p26vsih412jy` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `FKd6th9xowehhf1kmmq1dsseq28` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `FKghwsjbjo7mg3iufxruvq6iu3q` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK55d3hw331pn0ra4vlvgihcsyd` FOREIGN KEY (`card_order_id`) REFERENCES `card_order` (`id`),
  ADD CONSTRAINT `FK65des6cpwim2otj2tu3noh4ng` FOREIGN KEY (`address_order_id`) REFERENCES `address_order` (`id`),
  ADD CONSTRAINT `FKt9pocabfq29dvm6ybbygxly7q` FOREIGN KEY (`product_order_id`) REFERENCES `product_order` (`id`),
  ADD CONSTRAINT `FKtlqj1r93w541ryav9jd1eqlpb` FOREIGN KEY (`voucher_order_id`) REFERENCES `voucher_order` (`id`);

--
-- Các ràng buộc cho bảng `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  ADD CONSTRAINT `FKk3ndxg5xp6v7wd4gjyusp15gq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKdb050tk37qryv15hd932626th` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Các ràng buộc cho bảng `product_order`
--
ALTER TABLE `product_order`
  ADD CONSTRAINT `FKo7dl8saqqnhuc1e5pkoabjr1a` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Các ràng buộc cho bảng `user_permissions`
--
ALTER TABLE `user_permissions`
  ADD CONSTRAINT `FKe462ejfjrceotgioginphsva8` FOREIGN KEY (`user_id`) REFERENCES `employees` (`id`),
  ADD CONSTRAINT `FKq4qlrabt4s0etm9tfkoqfuib1` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`);

--
-- Các ràng buộc cho bảng `wishlists`
--
ALTER TABLE `wishlists`
  ADD CONSTRAINT `FK330pyw2el06fn5g28ypyljt16` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKl7ao98u2bm8nijc1rv4jobcrx` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
