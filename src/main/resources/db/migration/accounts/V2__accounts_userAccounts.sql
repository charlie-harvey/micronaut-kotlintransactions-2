-- accounts.userAccounts definition

CREATE TABLE `userAccounts` (
  `id` binary(16) NOT NULL COMMENT 'Primary key',
  `siteId` mediumint(8) unsigned NOT NULL,
  `currentGrade` varchar(2) DEFAULT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(72) DEFAULT NULL,
  `userDefinedPassword` varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `primaryLanguage` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'en',
  `applicationLanguage` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'en',
  `firstName` varchar(64) DEFAULT NULL,
  `lastName` varchar(64) DEFAULT NULL,
  `tobedeleted` tinyint(1) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `externalSourceId` char(36) NOT NULL,
  `dateSetDeleted` datetime DEFAULT NULL,
  `forcePasswordReset` tinyint(1) NOT NULL DEFAULT '0',
  `passwordSetByRoster` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_logins_username` (`siteId`,`username`),
  KEY `idx_active` (`active`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;