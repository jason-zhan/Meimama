
Create database meima;
use meima;

CREATE TABLE `meima`.`userinfo_back` (
  `idUserInfo` INT NOT NULL AUTO_INCREMENT,
  
`User_Name` VARCHAR(45) NULL,
 
 `Cell_Phone` VARCHAR(45) NOT NULL,
  
`User_Pwd` VARCHAR(45) NULL,
  
`User_nickname` VARCHAR(45) NULL,
  
`User_headpicurl` VARCHAR(250) NULL,
 
 `User_allmarks` INT NULL,
 
 `User_roleid` INT NULL,
 
 UNIQUE INDEX `idUserInfo_UNIQUE` (`idUserInfo` ASC),
  
PRIMARY KEY (`Cell_Phone`),
  
UNIQUE INDEX `Cell_Phone_UNIQUE` (`Cell_Phone` ASC))
COMMENT = 'user inforamtion';



CREATE TABLE `meima`.`fatherboard` (
  `idFatherBoard` INT NOT NULL AUTO_INCREMENT,
  `BiBo_title` VARCHAR(45) NULL,
  `BiBo_admin` VARCHAR(45) NULL,
  PRIMARY KEY (`idFatherBoard`),
  UNIQUE INDEX `idFatherBoard_UNIQUE` (`idFatherBoard` ASC));



CREATE TABLE `meima`.`childboard` (
  `idChildBoard` INT NOT NULL AUTO_INCREMENT,
  `SmBo_title` VARCHAR(45) NOT NULL,
  `SmBo_admin` VARCHAR(45) NULL,
  `SmBo_BiBoid` INT NULL,
  UNIQUE INDEX `idChildBoard_UNIQUE` (`idChildBoard` ASC),
  INDEX `fatherID_idx` (`SmBo_BiBoid` ASC),
  CONSTRAINT `fatherID`
    FOREIGN KEY (`SmBo_BiBoid`)
    REFERENCES `meima`.`fatherboard` (`idFatherBoard`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);




CREATE TABLE `meima`.`posts` (
  `Post_id` INT NOT NULL AUTO_INCREMENT,
  `Post_Title` VARCHAR(45) NULL,
  `Post_BiBoid` INT NULL,
  `Post_SmBoid` INT NULL,
  `Post_admin` VARCHAR(45) NULL,
  `Post_createtime` TIMESTAMP NULL DEFAULT now(),
  `Post_updatetime` TIMESTAMP NULL,
  `Post_content` VARCHAR(250) NULL,
  `Post_goodcount` INT NULL,
  `Post_badcount` INT NULL,
  `Post_reward` INT NULL,
  `Post_score` INT NULL,
  `Post_ispay` INT NULL,
  `Post_islocked` INT NULL,
  PRIMARY KEY (`Post_id`),
  UNIQUE INDEX `Post_id_UNIQUE` (`Post_id` ASC));


CREATE TABLE `meima`.`reply` (
  `idReply` INT NULL AUTO_INCREMENT,
  `Reply_admin` VARCHAR(45) NULL,
  `Reply_postid` INT NULL,
  `Reply_smboid` INT NULL,
  `Reply_biboid` INT NULL,
  `Reply_content` VARCHAR(250) NULL,
  `Reply_createtime` TIMESTAMP NULL DEFAULT now(),
  `Reply_goodcount` INT NULL,
  `Reply_badcount` INT NULL,
  `Reply_score` INT NULL,
  PRIMARY KEY (`idReply`),
  UNIQUE INDEX `idReply_UNIQUE` (`idReply` ASC));


CREATE TABLE `meima`.`postcommend` (
  `idPostCommend` INT NOT NULL AUTO_INCREMENT,
  `PoCo_commendtype` INT NULL,
  `PoCo_commendtime` TIMESTAMP NULL DEFAULT now(),
  `PoCo_commendperson` VARCHAR(45) NULL,
  `PoCo_commendreason` VARCHAR(45) NULL,
  PRIMARY KEY (`idPostCommend`),
  UNIQUE INDEX `idPostCommend_UNIQUE` (`idPostCommend` ASC));


CREATE TABLE `meima`.`dm_userroles` (
  `Role_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Role_name` VARCHAR(45) NULL,
  `Role_permission` VARCHAR(45) NULL,
  PRIMARY KEY (`Role_id`));CREATE TABLE `meima`.`dm_grade` (
  `idDM_Grade` INT NULL AUTO_INCREMENT,
  `Grade_name` VARCHAR(45) NULL,
  `Grade_medalurl` VARCHAR(45) NULL,
  PRIMARY KEY (`idDM_Grade`),
  UNIQUE INDEX `idDM_Grade_UNIQUE` (`idDM_Grade` ASC));



CREATE TABLE `meima`.`checkcode` (
  `idcheckcode` INT NOT NULL AUTO_INCREMENT,
  `cellphone` VARCHAR(45) NULL,
  `checkcode` VARCHAR(45) NULL,
  `ip` VARCHAR(45) NULL,
  `createAt` TIMESTAMP NULL DEFAULT now(),
  `expireAt` TIMESTAMP NULL,
  `isUse` INT NULL,
  `usingAt` TIMESTAMP NULL,
  UNIQUE INDEX `idcheckcode_UNIQUE` (`idcheckcode` ASC));

