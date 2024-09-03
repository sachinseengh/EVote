-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2024 at 01:08 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `evote`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `org_code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `phone`, `password`, `org_code`) VALUES
(2, '9800000001', 'Sachin@731', '0001');

-- --------------------------------------------------------

--
-- Table structure for table `admins_details`
--

CREATE TABLE `admins_details` (
  `id` int(11) NOT NULL,
  `org_name` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `org_code` varchar(7) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins_details`
--

INSERT INTO `admins_details` (`id`, `org_name`, `phone`, `password`, `org_code`, `logo`) VALUES
(1, 'Bernhardt College', '9800000001', 'Sachin@7310', '0001', '03-01.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `election`
--

CREATE TABLE `election` (
  `id` int(11) NOT NULL,
  `org_code` varchar(10) NOT NULL,
  `position` varchar(20) DEFAULT NULL,
  `candidate_one_name` varchar(30) DEFAULT NULL,
  `candidate_one_img` varchar(100) DEFAULT NULL,
  `candidate_two_name` varchar(30) DEFAULT NULL,
  `candidate_two_img` varchar(100) DEFAULT NULL,
  `Election_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `election`
--

INSERT INTO `election` (`id`, `org_code`, `position`, `candidate_one_name`, `candidate_one_img`, `candidate_two_name`, `candidate_two_img`, `Election_date`) VALUES
(1, '0001', 'CEO', 'Sachin', '03-01.jpg', 'Singh', 'barsaman-pun.jpg', '2024-03-21');

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `id` int(11) NOT NULL,
  `org_code` varchar(10) DEFAULT NULL,
  `post` varchar(50) DEFAULT NULL,
  `winner` varchar(25) DEFAULT NULL,
  `winner_img` varchar(100) DEFAULT NULL,
  `total_vote` int(11) DEFAULT NULL,
  `winby` int(11) DEFAULT NULL,
  `election_date` date DEFAULT NULL,
  `wish` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `org_code` varchar(10) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `citizenshipno` varchar(15) DEFAULT NULL,
  `dob` varchar(11) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `org_code`, `phone`, `citizenshipno`, `dob`, `remarks`, `status`) VALUES
(30, '0001', '9800000000', '12931500', '2024-03-07', '', 'Approved');

-- --------------------------------------------------------

--
-- Table structure for table `unverified_voters`
--

CREATE TABLE `unverified_voters` (
  `org_code` varchar(10) NOT NULL,
  `id` int(11) NOT NULL,
  `fullname` varchar(50) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `dob` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `citizenshipno` varchar(15) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `employee_id` varchar(100) DEFAULT NULL,
  `citizenship_front` varchar(100) DEFAULT NULL,
  `citizenship_back` varchar(100) DEFAULT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `voters`
--

CREATE TABLE `voters` (
  `id` int(11) NOT NULL,
  `org_code` varchar(10) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `dob` varchar(15) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `citizenshipno` varchar(20) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `employee_id` varchar(100) DEFAULT NULL,
  `citizenshipfront` varchar(100) DEFAULT NULL,
  `citizenshipback` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `voters`
--

INSERT INTO `voters` (`id`, `org_code`, `name`, `email`, `phone`, `dob`, `address`, `citizenshipno`, `photo`, `employee_id`, `citizenshipfront`, `citizenshipback`) VALUES
(30, '0001', 'Sachin Kumar Singh', 'Sachin@gmail.com', '9800000000', '2024-03-07', 'Gaushala-11,Mahottari', '12931500', '7775.jpg', '03-01.jpg', '207719960.jpg', 'barsaman-pun.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `voter_login`
--

CREATE TABLE `voter_login` (
  `id` int(11) NOT NULL,
  `org_code` varchar(10) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `voter_login`
--

INSERT INTO `voter_login` (`id`, `org_code`, `phone`, `password`) VALUES
(30, '0001', '9800000000', 'Sachin@7310');

-- --------------------------------------------------------

--
-- Table structure for table `votes`
--

CREATE TABLE `votes` (
  `id` int(11) NOT NULL,
  `citizenshipno` varchar(20) DEFAULT NULL,
  `org_code` varchar(10) DEFAULT NULL,
  `votefor` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `admins_details`
--
ALTER TABLE `admins_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `election`
--
ALTER TABLE `election`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `org_code` (`org_code`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `unverified_voters`
--
ALTER TABLE `unverified_voters`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `voters`
--
ALTER TABLE `voters`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `voter_login`
--
ALTER TABLE `voter_login`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `votes`
--
ALTER TABLE `votes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `citizenshipno` (`citizenshipno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `admins_details`
--
ALTER TABLE `admins_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `election`
--
ALTER TABLE `election`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `unverified_voters`
--
ALTER TABLE `unverified_voters`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `votes`
--
ALTER TABLE `votes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
