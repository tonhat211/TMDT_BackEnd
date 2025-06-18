-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 23, 2025 lúc 11:53 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

-- REMOVED: 'show tables' command (must come AFTER database/table creation)
-- MOVED: All SET commands to the top
-- ADDED: Semicolons for all statements

-- 1. Set SQL mode FIRST (critical)
SET SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO';
SET time_zone = '+00:00';

-- 2. Character set configuration
SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET NAMES utf8mb4;

-- 3. Database creation (must come AFTER character sets)
CREATE DATABASE IF NOT EXISTS `passfashion_db`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

-- 4. Select the database
USE `passfashion_db`;


-- 6. Start transaction for table operations
START TRANSACTION;
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
  `is_default` int(11) NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `ward` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--
CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `is_deleted` int(11) DEFAULT 0,
  `link` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `is_deleted`, `link`, `title`) VALUES
(1, 0, 'quan-ao', 'Quần áo'),
(2, 0, 'giay-dep', 'Giày dép'),
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
  `is_deleted` int(11) NOT NULL,
  `level` int(11) NOT NULL DEFAULT 0,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `comments`
--
INSERT INTO `comments` (`id`, `content`, `created_at`, `is_deleted`, `level`, `product_id`, `user_id`, `parent_id`) VALUES
(1, 'Hàng còn không', '2025-05-01 11:45:07', 0, 0, 2, 2, NULL),
(2, 'Còn bạn ơi', '2025-05-23 11:45:31', 0, 1, 2, 1, 1),
(3, 'B còn màu nào', '2025-05-23 11:49:45', 0, 1, 2, 2, 1),
(4, 'Màu tím', '2025-05-23 11:50:32', 0, 1, 2, 1, 1),
(5, 'Giảm 50% không', '2025-05-23 11:50:56', 0, 0, 2, 3, NULL),
(6, 'Giảm 20% thoi b', '2025-05-23 16:18:44', 0, 1, 2, 1, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employees`
--
users
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
  `alt` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `images`
--

INSERT INTO `images` (`id`, `alt`, `url`) VALUES
(1, 'quan ao', 'quan-jean.png'),
(2, 'ao-champion.jpg\r\n', 'ao-champion.jpg'),
(3, 'kinh-channel.png\r\n', 'kinh-channel.png'),
(4, 'avatar.jpg', 'avatar.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image_products`
--

CREATE TABLE `image_products` (
  `product_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `image_products`
--
INSERT INTO `image_products` (`product_id`, `image_id`) VALUES
(2, 1),
(2, 3),
(2, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permissions`
--
CREATE TABLE `permissions` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `description` varchar(255) DEFAULT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT 0,
  `is_sold` int(11) NOT NULL DEFAULT 0,
  `price` double NOT NULL DEFAULT 0,
  `category_id` bigint(20) DEFAULT NULL,
  `image_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `thumbnail` bigint(20) DEFAULT NULL,
  `qty` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
-- id, name, price, category, user, qty, description, image_id, 
INSERT INTO `products` (`id`, `name`, `created_at`, `description`, `is_deleted`, `is_sold`, `price`, `category_id`, `image_id`, `user_id`, `qty`) VALUES
(1, 'Quần jean da bò Crocodie', '2025-05-14 21:18:15', 'Đẹp', 0, 0, 100000, 1, 1, 1, 1),
(2, 'Ao thun mau den', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 200000, 1, 1, 1, 1),
(3, 'Túi channel', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 1000000, 3, 1, 1, 1),
(4, 'Ví vans', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 150000, 3, 1, 1, 1),
(5, 'Đồng hồ timex', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 15000000, 4, 1, 1, 1),
(6, 'Giày adidas size 39', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 600000, 2, 1, 1, 1),
(7, 'Dép nike size 27', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 99000, 2, 1, 1, 1),
(8, 'Quần đùi', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 100000, 1, 1, 1, 1),
(9, 'Áo dài', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 200000, 1, 1, 1, 1),
(10, 'Áo khoác tuyển đức', '2025-05-14 21:33:02', 'Đẹp', 0, 0, 100000, 1, 1, 1, 1),
(11, 'Quan ao test', '2025-05-22 15:43:48', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(12, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(13, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(14, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(15, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(16, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(17, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(18, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(19, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(20, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(21, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(22, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(23, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(24, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(25, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(26, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(27, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(28, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(29, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(30, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(31, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(32, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(33, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(34, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(35, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(36, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(37, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(38, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(39, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(40, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(41, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(42, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(43, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1),
(44, 'Quan ao test', '2025-05-22 15:44:18', 'DDejp', 0, 0, 0, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `is_deleted` int(11) NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `saled_order_qty` int(11) NOT NULL DEFAULT 0,
  `rating` int(11) NOT NULL DEFAULT 0,
  `sold_order_qty` int(11) NOT NULL DEFAULT 0,
  `total_review` int(11) NOT NULL DEFAULT 0,
  `image_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--
INSERT INTO `users` (`id`, `birthday`, `email`, `is_deleted`, `name`, `pwd`, `saled_order_qty`, `rating`, `sold_order_qty`, `total_review`, `image_id`) VALUES
(1, NULL, 'tonhat@gmail.com', 0, 'To Nhat', '1234', 0, 0, 0, 0, 4),
(2, NULL, 'tri@gmail.com', 0, 'Tri', '1234', 0, 0, 0, 0, 4),
(3, NULL, 'hau@gmail.com', 0, 'Hau', '1234', 0, 0, 0, 0, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_permissions`
--

CREATE TABLE `user_permissions` (
  `user_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  ADD KEY `FK6uv0qku8gsu6x1r2jkrtqwjtn` (`product_id`),
  ADD KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  ADD KEY `FKlri30okf66phtcgbe5pok7cc0` (`parent_id`);

--
-- Chỉ mục cho bảng `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `image_products`
--
ALTER TABLE `image_products`
  ADD KEY `FKral42yw396l5lidvjd956agbu` (`image_id`),
  ADD KEY `FKijssphiq42gssewps1ujpgvj3` (`product_id`);

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
  ADD KEY `FKn18ti2byyc5pbjr9cpjj7qkl9` (`image_id`),
  ADD KEY `FKdb050tk37qryv15hd932626th` (`user_id`),
  ADD KEY `FKrygc2rcbb44dg9ew8nre1c3pf` (`thumbnail`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK17herqt2to4hyl5q5r5ogbxk9` (`image_id`);

--
-- Chỉ mục cho bảng `user_permissions`
--
ALTER TABLE `user_permissions`
  ADD KEY `FKq4qlrabt4s0etm9tfkoqfuib1` (`permission_id`),
  ADD KEY `FKe462ejfjrceotgioginphsva8` (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `addresses`
--
ALTER TABLE `addresses`
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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `images`
--
ALTER TABLE `images`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `permissions`
--
ALTER TABLE `permissions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
-- Các ràng buộc cho bảng `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `FKd6th9xowehhf1kmmq1dsseq28` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `image_products`
--
ALTER TABLE `image_products`
  ADD CONSTRAINT `FKijssphiq42gssewps1ujpgvj3` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKral42yw396l5lidvjd956agbu` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKdb050tk37qryv15hd932626th` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKn18ti2byyc5pbjr9cpjj7qkl9` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`),
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `FKrygc2rcbb44dg9ew8nre1c3pf` FOREIGN KEY (`thumbnail`) REFERENCES `images` (`id`);

--
-- Các ràng buộc cho bảng `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK17herqt2to4hyl5q5r5ogbxk9` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`);

--
-- Các ràng buộc cho bảng `user_permissions`
--
ALTER TABLE `user_permissions`
  ADD CONSTRAINT `FKe462ejfjrceotgioginphsva8` FOREIGN KEY (`user_id`) REFERENCES `employees` (`id`),
  ADD CONSTRAINT `FKq4qlrabt4s0etm9tfkoqfuib1` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- 7. Commit transaction and restore settings
COMMIT;

SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;
