-- MariaDB dump 10.17  Distrib 10.4.6-MariaDB, for osx10.14 (x86_64)
--
-- Host: sistemasanitariodb.c16q8irjv9nd.us-east-2.rds.amazonaws.com    Database: sistemasanitario
-- ------------------------------------------------------
-- Server version	10.2.21-MariaDB-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AUTH_TOKENS`
--

DROP TABLE IF EXISTS `AUTH_TOKENS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AUTH_TOKENS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selector` char(36) NOT NULL,
  `validator` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `selector` (`selector`),
  KEY `userId` (`userId`),
  KEY `selectorIndex` (`selector`),
  CONSTRAINT `AUTH_TOKENS_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTH_TOKENS`
--

LOCK TABLES `AUTH_TOKENS` WRITE;
/*!40000 ALTER TABLE `AUTH_TOKENS` DISABLE KEYS */;
/*!40000 ALTER TABLE `AUTH_TOKENS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ESAME_LABORATORIO`
--

DROP TABLE IF EXISTS `ESAME_LABORATORIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ESAME_LABORATORIO` (
  `idPrescrizione` int(11) NOT NULL,
  `idSsp` int(11) NOT NULL,
  `idReport` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPrescrizione`),
  KEY `idSsp` (`idSsp`),
  KEY `idReport` (`idReport`),
  CONSTRAINT `ESAME_LABORATORIO_ibfk_1` FOREIGN KEY (`idPrescrizione`) REFERENCES `PRESCRIZIONE_ESAME` (`id`),
  CONSTRAINT `ESAME_LABORATORIO_ibfk_2` FOREIGN KEY (`idSsp`) REFERENCES `SSP` (`id`),
  CONSTRAINT `ESAME_LABORATORIO_ibfk_3` FOREIGN KEY (`idReport`) REFERENCES `REPORT` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ESAME_LABORATORIO`
--

LOCK TABLES `ESAME_LABORATORIO` WRITE;
/*!40000 ALTER TABLE `ESAME_LABORATORIO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ESAME_LABORATORIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ESAME_PRESCRIVIBILE`
--

DROP TABLE IF EXISTS `ESAME_PRESCRIVIBILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ESAME_PRESCRIVIBILE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descrizione` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ESAME_PRESCRIVIBILE`
--

LOCK TABLES `ESAME_PRESCRIVIBILE` WRITE;
/*!40000 ALTER TABLE `ESAME_PRESCRIVIBILE` DISABLE KEYS */;
INSERT INTO `ESAME_PRESCRIVIBILE` VALUES (20,'Estrazione di dente deciduo (gratuita fino a 14 anni)','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(21,'Estrazione di dente permanente (gratuita fino a 14 anni)','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(22,'Altra estrazione chirurgica dente (gratuita fino a 14 anni)','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(23,'Ricostruzione dente con otturazione (gratuita fino a 14 anni solo in caso di evento traumatico)','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(24,'Ricostruzione dente mediante otturazione a tre o più superfici','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(25,'Ricostruzione dente mediante intarsio','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(26,'Applicazione di corona','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(27,'Applicazione di corona in lega aurea','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(28,'Altra applicazione corona','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(29,'Applicazione corona e perno','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(30,'Altra applicazione corona e perno','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(31,'Inserzione di ponte fisso','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(32,'Inserzione di protesi rimovibile','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(33,'Altra inserzione di protesi','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(34,'Inserzione di protesi provvisoria','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(35,'Altra riparazione dentaria','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(36,'Impianto di dente (gratuita fino a 14 anni)','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(37,'Impianto di protesi dentaria','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(38,'Terapia canalare in monoradicolato','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(39,'Terapia canalare in pluriradicolato','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(40,'Apicectomia','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(41,'Gengivoplastica','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(42,'Asportazione di tessuto della gengiva (gratuita fino a 14 anni)','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(43,'Levigatura delle radici','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(44,'Intervento chirurgico preprotesico','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(45,'Asportazione di lesione dentaria della mandibola (gratuita fino a 14 anni)','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(46,'Trattamento ortodontico con apparecchi mobili','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(47,'Trattamento con apparecchi fissi','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(48,'Trattamento con apparecchi funzionali','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(49,'Analisi citogenetica per fragilita cromosomica','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(50,'Analisi citogenetica per ricerca siti fragili','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(51,'Analisi citogenetica per scambi di cromatidi','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(52,'Analisi citogenetica per studio mosaicismo','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(53,'Analisi citogenetica per studio riarrangiamenti','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(54,'Analisi DNA e ibridazione con sonda','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(55,'Analisi DNA per polimorfismo','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(56,'Analisi mutazione del DNA con reazione polimerasica a catena','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(57,'Analisi mutazione del DNA con ibridazione sonde non radiomarcate','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(58,'Analisi mutazione del DNA con ibridazione sonde radiomarcate','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(59,'Analisi mutazione del DNA con reverse dot blot','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita'),(60,'Analisi di polimorfismi','\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed vulputate tellus, vel consequat felis. Mauris consectetur elementum nisl at interdum. Donec eu enim sed diam ultrices porta quis ut neque. Sed luctus sem ac ex scelerisque facilisis. Donec quis.'),(61,'Analisi di segmenti di DNA','Questa e la descrizione dell esame, esso e garantito dal sistema sanitario e dal progetto sanita unita');
/*!40000 ALTER TABLE `ESAME_PRESCRIVIBILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ESAME_SPECIALISTICO`
--

DROP TABLE IF EXISTS `ESAME_SPECIALISTICO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ESAME_SPECIALISTICO` (
  `idPrescrizione` int(11) NOT NULL,
  `idMedicoSpe` int(11) NOT NULL,
  `idReport` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPrescrizione`),
  KEY `idMedicoSpe` (`idMedicoSpe`),
  KEY `idReport` (`idReport`),
  CONSTRAINT `ESAME_SPECIALISTICO_ibfk_1` FOREIGN KEY (`idPrescrizione`) REFERENCES `PRESCRIZIONE_ESAME` (`id`),
  CONSTRAINT `ESAME_SPECIALISTICO_ibfk_2` FOREIGN KEY (`idMedicoSpe`) REFERENCES `MEDICO_SPECIALISTA` (`id`),
  CONSTRAINT `ESAME_SPECIALISTICO_ibfk_3` FOREIGN KEY (`idReport`) REFERENCES `REPORT` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ESAME_SPECIALISTICO`
--

LOCK TABLES `ESAME_SPECIALISTICO` WRITE;
/*!40000 ALTER TABLE `ESAME_SPECIALISTICO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ESAME_SPECIALISTICO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEDICINA`
--

DROP TABLE IF EXISTS `MEDICINA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEDICINA` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descrizione` text NOT NULL,
  `prezzo` decimal(4,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEDICINA`
--

LOCK TABLES `MEDICINA` WRITE;
/*!40000 ALTER TABLE `MEDICINA` DISABLE KEYS */;
INSERT INTO `MEDICINA` VALUES (1,'Abacavir Sulfate','Descrizione di Abacavir Sulfate',1.41),(2,'Abatacept','Descrizione di Abatacept',85.67),(3,'Abilify','Descrizione di Abilify',64.34),(4,'Acamprosate Calcium','Descrizione di Acamprosate Calcium',66.00),(5,'Accretropin','Descrizione di Accretropin',92.69),(6,'Aceon','Descrizione di Aceon',58.24),(7,'Aci-Jel','Descrizione di Aci-Jel',15.78),(8,'Acthrel','Descrizione di Acthrel',94.58),(9,'Actimmune','Descrizione di Actimmune',70.62),(10,'Actisite','Descrizione di Actisite',45.64),(11,'Acular','Descrizione di Acular',58.05),(12,'Acular LS','Descrizione di Acular LS',82.45),(13,'Acuvail','Descrizione di Acuvail',33.81),(14,'Adagen','Descrizione di Adagen',69.27),(15,'Adapalene','Descrizione di Adapalene',0.61),(16,'Adcirca','Descrizione di Adcirca',5.91),(17,'Adefovir Dipivoxil','Descrizione di Adefovir Dipivoxil',30.95),(18,'Adenoscan','Descrizione di Adenoscan',20.42),(19,'Adenosine','Descrizione di Adenosine',49.27),(20,'Adipex-P','Descrizione di Adipex-P',55.36),(21,'AdreView','Descrizione di AdreView',24.91),(22,'Advair HFA','Descrizione di Advair HFA',47.04),(23,'Aerospan HFA','Descrizione di Aerospan HFA',40.02),(24,'Agalsidase Beta','Descrizione di Agalsidase Beta',2.53),(25,'Aggrenox','Descrizione di Aggrenox',3.92),(26,'Akineton','Descrizione di Akineton',24.82),(27,'Alamast','Descrizione di Alamast',75.21),(28,'Albenza','Descrizione di Albenza',88.16),(29,'Aldactazide','Descrizione di Aldactazide',98.18),(30,'Aldactone','Descrizione di Aldactone',99.95),(31,'Aldoril','Descrizione di Aldoril',55.47),(32,'Aldurazyme','Descrizione di Aldurazyme',18.26),(33,'Alemtuzumab','Descrizione di Alemtuzumab',48.71),(34,'Alglucosidase Alfa','Descrizione di Alglucosidase Alfa',16.38),(35,'Allegra-D','Descrizione di Allegra-D',19.69),(36,'Allegra D 24 Hour','Descrizione di Allegra D 24 Hour',0.12),(37,'Alli','Descrizione di Alli',57.67),(38,'Aloprim','Descrizione di Aloprim',63.99),(39,'Alora','Descrizione di Alora',71.35),(40,'Alphanate','Descrizione di Alphanate',99.94),(41,'Altace','Descrizione di Altace',88.03),(42,'Altocor','Descrizione di Altocor',39.11),(43,'Altoprev','Descrizione di Altoprev',14.22),(44,'Alupent','Descrizione di Alupent',4.33),(45,'Amantadine Hydrochloride','Descrizione di Amantadine Hydrochloride',77.73),(46,'Amerge','Descrizione di Amerge',47.64),(47,'Amifostine','Descrizione di Amifostine',52.41),(48,'Amiloride','Descrizione di Amiloride',78.11),(49,'Aminosalicylic Acid','Descrizione di Aminosalicylic Acid',83.53),(50,'Aminosyn II 8.5%','Descrizione di Aminosyn II 8.5%',69.68),(51,'Amlodipine Besylate','Descrizione di Amlodipine Besylate',56.47),(52,'Amoxapine','Descrizione di Amoxapine',77.44),(53,'Amytal Sodium','Descrizione di Amytal Sodium',27.62),(54,'Anabolic steroids','Descrizione di Anabolic steroids',28.57),(55,'Anadrol-50','Descrizione di Anadrol-50',1.37),(56,'Antithrombin','Descrizione di Antithrombin',29.59),(57,'Antivenin','Descrizione di Antivenin',88.23),(58,'Antivert','Descrizione di Antivert',98.41),(59,'Aredia','Descrizione di Aredia',76.29),(60,'Aricept','Descrizione di Aricept',8.78),(61,'Armodafinil','Descrizione di Armodafinil',24.16),(62,'Arranon','Descrizione di Arranon',31.35),(63,'Artane','Descrizione di Artane',22.90),(64,'Asclera','Descrizione di Asclera',19.42),(65,'Ascorbic Acid','Descrizione di Ascorbic Acid',3.88),(66,'Astemizole','Descrizione di Astemizole',2.06),(67,'Atacand','Descrizione di Atacand',91.40),(68,'Atacand HCT','Descrizione di Atacand HCT',90.42),(69,'Atazanavir Sulfate','Descrizione di Atazanavir Sulfate',93.64),(70,'Atomoxetine HCl','Descrizione di Atomoxetine HCl',27.48),(71,'Atridox','Descrizione di Atridox',75.46),(72,'Atripla','Descrizione di Atripla',39.05),(73,'Atropen','Descrizione di Atropen',59.90),(74,'Augmentin XR','Descrizione di Augmentin XR',68.29),(75,'Avage','Descrizione di Avage',44.70),(76,'Avandia','Descrizione di Avandia',54.50),(77,'Avastin','Descrizione di Avastin',51.06),(78,'Avinza','Descrizione di Avinza',12.01),(79,'Axid','Descrizione di Axid',44.93),(80,'Azasan','Descrizione di Azasan',36.48),(81,'Azasite','Descrizione di Azasite',97.29),(82,'Azelaic Acid','Descrizione di Azelaic Acid',27.23),(83,'Azelastine Hydrochloride','Descrizione di Azelastine Hydrochloride',41.84),(84,'Azilect','Descrizione di Azilect',0.54),(85,'Azmacort','Descrizione di Azmacort',88.56),(86,'Balsalazide','Descrizione di Balsalazide',19.40),(87,'Benazepril','Descrizione di Benazepril',50.66),(88,'Benzocaine','Descrizione di Benzocaine',74.76),(89,'Benzoyl Peroxide Gel','Descrizione di Benzoyl Peroxide Gel',40.31),(90,'Benzphetamine','Descrizione di Benzphetamine',64.08),(91,'Benztropine Mesylate','Descrizione di Benztropine Mesylate',70.44),(92,'Bepreve','Descrizione di Bepreve',25.39),(93,'Betagan','Descrizione di Betagan',47.26),(94,'Bethanechol','Descrizione di Bethanechol',14.23),(95,'Betimol','Descrizione di Betimol',56.37),(96,'Betoptic S','Descrizione di Betoptic S',16.38),(97,'Bevacizumab','Descrizione di Bevacizumab',62.18),(98,'BiCNU','Descrizione di BiCNU',21.82),(99,'Biperiden','Descrizione di Biperiden',30.29),(100,'Bismuth Subcitrate Potassium','Descrizione di Bismuth Subcitrate Potassium',66.41),(101,'Bismuth Subsalicylate','Descrizione di Bismuth Subsalicylate',49.33),(102,'Blocadren','Descrizione di Blocadren',12.15),(103,'Boniva','Descrizione di Boniva',47.39),(104,'Bontril','Descrizione di Bontril',97.58),(105,'Boostrix','Descrizione di Boostrix',28.04),(106,'Botulinum Toxin Type A','Descrizione di Botulinum Toxin Type A',0.30),(107,'Bravelle','Descrizione di Bravelle',40.77),(108,'Brevibloc','Descrizione di Brevibloc',24.06),(109,'Bromocriptine Mesylate','Descrizione di Bromocriptine Mesylate',17.73),(110,'Brovana','Descrizione di Brovana',24.86),(111,'Budesonide','Descrizione di Budesonide',51.21),(112,'Buprenorphine','Descrizione di Buprenorphine',88.45),(113,'Buspar','Descrizione di Buspar',70.24),(114,'Buspirone','Descrizione di Buspirone',91.72),(115,'Busulfan','Descrizione di Busulfan',63.70),(116,'Busulfex','Descrizione di Busulfex',59.29),(117,'Cabergoline','Descrizione di Cabergoline',68.77),(118,'Caduet','Descrizione di Caduet',56.73),(119,'Calcitonin-Salmon','Descrizione di Calcitonin-Salmon',51.97),(120,'Calcium Chloride','Descrizione di Calcium Chloride',66.12),(121,'Calcium Disodium Versenate','Descrizione di Calcium Disodium Versenate',40.86),(122,'Calcium Gluconate','Descrizione di Calcium Gluconate',33.90),(123,'Campral','Descrizione di Campral',92.61),(124,'Canasa','Descrizione di Canasa',87.36),(125,'Cancidas','Descrizione di Cancidas',24.55),(126,'Captopril','Descrizione di Captopril',48.67),(127,'Carac','Descrizione di Carac',37.55),(128,'Carbatrol','Descrizione di Carbatrol',56.74),(129,'Cardiolite','Descrizione di Cardiolite',41.31),(130,'Carisoprodol','Descrizione di Carisoprodol',21.52),(131,'Carmustine','Descrizione di Carmustine',74.50),(132,'Carvedilol','Descrizione di Carvedilol',12.50),(133,'Casodex','Descrizione di Casodex',70.41),(134,'Caspofungin Acetate','Descrizione di Caspofungin Acetate',18.24),(135,'Cataflam','Descrizione di Cataflam',40.66),(136,'Catapres','Descrizione di Catapres',35.30),(137,'Catapres-TTS','Descrizione di Catapres-TTS',12.07),(138,'Caverject','Descrizione di Caverject',2.91),(139,'Cedax','Descrizione di Cedax',81.07),(140,'Cefditoren Pivoxil','Descrizione di Cefditoren Pivoxil',14.37),(141,'Cefixime','Descrizione di Cefixime',55.57),(142,'Cefizox','Descrizione di Cefizox',23.87),(143,'Cefotetan','Descrizione di Cefotetan',78.53),(144,'Ceftazidime','Descrizione di Ceftazidime',4.83),(145,'Ceftibuten','Descrizione di Ceftibuten',50.45),(146,'Ceftin','Descrizione di Ceftin',90.09),(147,'Cefzil','Descrizione di Cefzil',23.09),(148,'Celestone Soluspan','Descrizione di Celestone Soluspan',98.58),(149,'Celexa','Descrizione di Celexa',43.21),(150,'CellCept','Descrizione di CellCept',86.88),(151,'Cellulose','Descrizione di Cellulose',65.22),(152,'Celontin','Descrizione di Celontin',50.46),(153,'Cephalexin','Descrizione di Cephalexin',76.06),(154,'Cerebyx','Descrizione di Cerebyx',31.30),(155,'Ceretec','Descrizione di Ceretec',65.13),(156,'Cerubidine','Descrizione di Cerubidine',92.68),(157,'Cerumenex','Descrizione di Cerumenex',10.00),(158,'Cervidil','Descrizione di Cervidil',26.91),(159,'Cetirizine','Descrizione di Cetirizine',88.62),(160,'Cetraxal','Descrizione di Cetraxal',17.55),(161,'Cetrotide','Descrizione di Cetrotide',75.10),(162,'Cetuximab','Descrizione di Cetuximab',64.59),(163,'Chantix','Descrizione di Chantix',77.24),(164,'Chibroxin','Descrizione di Chibroxin',6.37),(165,'Chlorambucil','Descrizione di Chlorambucil',16.48),(166,'Chloramphenicol Sodium Succinate','Descrizione di Chloramphenicol Sodium Succinate',65.83),(167,'Chloroprocaine','Descrizione di Chloroprocaine',76.95),(168,'Chlorpheniramine Maleate','Descrizione di Chlorpheniramine Maleate',41.41),(169,'Chlorpromazine','Descrizione di Chlorpromazine',37.02),(170,'Chlorpropamide','Descrizione di Chlorpropamide',44.50),(171,'Chlorthalidone','Descrizione di Chlorthalidone',3.91),(172,'Cholera Vaccine','Descrizione di Cholera Vaccine',9.36),(173,'Chorionic Gonadotropin','Descrizione di Chorionic Gonadotropin',94.74),(174,'Ciclopirox Gel','Descrizione di Ciclopirox Gel',11.20),(175,'Cilostazol','Descrizione di Cilostazol',46.96),(176,'Cinobac','Descrizione di Cinobac',41.21),(177,'Cipro','Descrizione di Cipro',74.48),(178,'Cipro XR','Descrizione di Cipro XR',32.99),(179,'Cisapride','Descrizione di Cisapride',97.68),(180,'Clarinex','Descrizione di Clarinex',45.84),(181,'Clarithromycin','Descrizione di Clarithromycin',83.81),(182,'Claritin','Descrizione di Claritin',48.34),(183,'Cleocin','Descrizione di Cleocin',1.53),(184,'Cleviprex','Descrizione di Cleviprex',20.99),(185,'Climara Pro','Descrizione di Climara Pro',65.18),(186,'Clinoril','Descrizione di Clinoril',80.38),(187,'Clobetasol Propionate','Descrizione di Clobetasol Propionate',70.00),(188,'Clocortolone','Descrizione di Clocortolone',38.88),(189,'Clofarabine','Descrizione di Clofarabine',82.27),(190,'Clonidine','Descrizione di Clonidine',5.67),(191,'Clorazepate Dipotassium','Descrizione di Clorazepate Dipotassium',38.28),(192,'Clorpres','Descrizione di Clorpres',49.93),(193,'Clotrimazole','Descrizione di Clotrimazole',47.48),(194,'Cocaine','Descrizione di Cocaine',25.83),(195,'Codeine','Descrizione di Codeine',79.07),(196,'Cognex','Descrizione di Cognex',25.21),(197,'Colazal','Descrizione di Colazal',44.10),(198,'Colchicine','Descrizione di Colchicine',67.17),(199,'Colcrys','Descrizione di Colcrys',29.13),(200,'Colesevelam Hcl','Descrizione di Colesevelam Hcl',96.14),(201,'Combivir','Descrizione di Combivir',44.09),(202,'Conjugated Estrogens','Descrizione di Conjugated Estrogens',77.16),(203,'Copaxone','Descrizione di Copaxone',90.35),(204,'Corgard','Descrizione di Corgard',75.51),(205,'Cosmegen','Descrizione di Cosmegen',7.00),(206,'Coumadin','Descrizione di Coumadin',53.49),(207,'Crolom','Descrizione di Crolom',66.19),(208,'Cromolyn Sodium','Descrizione di Cromolyn Sodium',16.56),(209,'Cubicin','Descrizione di Cubicin',28.98),(210,'Curosurf','Descrizione di Curosurf',4.03),(211,'Cuvposa','Descrizione di Cuvposa',63.24),(212,'Cyanocobalamin','Descrizione di Cyanocobalamin',11.08),(213,'Cyclobenzaprine Hcl','Descrizione di Cyclobenzaprine Hcl',59.44),(214,'Cyclophosphamide','Descrizione di Cyclophosphamide',27.09),(215,'Cyclosporine','Descrizione di Cyclosporine',50.89),(216,'Cylert','Descrizione di Cylert',28.02),(217,'Cymbalta','Descrizione di Cymbalta',32.95),(218,'Cyproheptadine','Descrizione di Cyproheptadine',5.85),(219,'Cystadane','Descrizione di Cystadane',31.93),(220,'Cytogam','Descrizione di Cytogam',44.43),(221,'Cytomel','Descrizione di Cytomel',6.23),(222,'Dacarbazine','Descrizione di Dacarbazine',16.87),(223,'Daraprim','Descrizione di Daraprim',94.14),(224,'Darvocet-N','Descrizione di Darvocet-N',96.03),(225,'Darvon Compound','Descrizione di Darvon Compound',75.48),(226,'Dasatinib','Descrizione di Dasatinib',53.00),(227,'Daunorubicin','Descrizione di Daunorubicin',35.58),(228,'Daypro','Descrizione di Daypro',67.18),(229,'Daypro Alta','Descrizione di Daypro Alta',6.80),(230,'DDAVP Nasal Spray','Descrizione di DDAVP Nasal Spray',98.96),(231,'Demadex','Descrizione di Demadex',48.98),(232,'Demeclocycline HCl','Descrizione di Demeclocycline HCl',53.81),(233,'Demser','Descrizione di Demser',96.89),(234,'Depacon','Descrizione di Depacon',8.98),(235,'DepoDur','Descrizione di DepoDur',81.09),(236,'Desferal','Descrizione di Desferal',72.57),(237,'Desogen','Descrizione di Desogen',5.72),(238,'Desonate','Descrizione di Desonate',37.22),(239,'DesOwen','Descrizione di DesOwen',86.38),(240,'Detrol','Descrizione di Detrol',23.92),(241,'Detrol LA','Descrizione di Detrol LA',61.38),(242,'Dexlansoprazole','Descrizione di Dexlansoprazole',42.79),(243,'Dexmethylphenidate Hydrochloride','Descrizione di Dexmethylphenidate Hydrochloride',82.90),(244,'Dexrazoxane','Descrizione di Dexrazoxane',97.57),(245,'Diamox Sequels','Descrizione di Diamox Sequels',80.58),(246,'Dicyclomine','Descrizione di Dicyclomine',62.91),(247,'Didanosine','Descrizione di Didanosine',99.15),(248,'Diethylpropion','Descrizione di Diethylpropion',29.88),(249,'Differin','Descrizione di Differin',92.56),(250,'Diflucan','Descrizione di Diflucan',16.11),(251,'Digoxin Immune Fab','Descrizione di Digoxin Immune Fab',63.02),(252,'Diovan HCT','Descrizione di Diovan HCT',27.34),(253,'Diphenhydramine','Descrizione di Diphenhydramine',43.72),(254,'Diphtheria-Tetanus Vaccine','Descrizione di Diphtheria-Tetanus Vaccine',1.55),(255,'Diprolene AF','Descrizione di Diprolene AF',4.28),(256,'Dipyridamole','Descrizione di Dipyridamole',27.46),(257,'Ditropan','Descrizione di Ditropan',64.62),(258,'Dobutamine','Descrizione di Dobutamine',49.86),(259,'Dofetilide','Descrizione di Dofetilide',89.75),(260,'Dolophine','Descrizione di Dolophine',85.33),(261,'Donepezil Hydrochloride','Descrizione di Donepezil Hydrochloride',99.69),(262,'Dopamine Hydrochloride','Descrizione di Dopamine Hydrochloride',2.42),(263,'Dopar','Descrizione di Dopar',39.44),(264,'Dopram','Descrizione di Dopram',15.16),(265,'Doral','Descrizione di Doral',19.81),(266,'Doryx','Descrizione di Doryx',20.98),(267,'Dorzolamide','Descrizione di Dorzolamide',4.22),(268,'Dovonex','Descrizione di Dovonex',87.51),(269,'Doxacurium Chloride','Descrizione di Doxacurium Chloride',1.21),(270,'Doxapram','Descrizione di Doxapram',57.99),(271,'Doxazosin Mesylate','Descrizione di Doxazosin Mesylate',36.57),(272,'Doxepin','Descrizione di Doxepin',85.76),(273,'Doxercalciferol','Descrizione di Doxercalciferol',79.92),(274,'Doxil','Descrizione di Doxil',44.89),(275,'Doxycycline','Descrizione di Doxycycline',51.75),(276,'Doxycycline Hyclate','Descrizione di Doxycycline Hyclate',8.12),(277,'Drisdol','Descrizione di Drisdol',27.00),(278,'Dronabinol','Descrizione di Dronabinol',26.10),(279,'Drospirenone and Estradiol','Descrizione di Drospirenone and Estradiol',11.03),(280,'Duetact','Descrizione di Duetact',69.69),(281,'Duraclon','Descrizione di Duraclon',79.61),(282,'Dynacirc','Descrizione di Dynacirc',47.88),(283,'Dynacirc CR','Descrizione di Dynacirc CR',35.01),(284,'Dynapen','Descrizione di Dynapen',98.89),(285,'Dyphylline','Descrizione di Dyphylline',53.55),(286,'Econazole Nitrate','Descrizione di Econazole Nitrate',65.23),(287,'Edrophonium','Descrizione di Edrophonium',51.02),(288,'Efavirenz','Descrizione di Efavirenz',6.85),(289,'Elaprase','Descrizione di Elaprase',42.82),(290,'Elavil','Descrizione di Elavil',3.85),(291,'Eletriptan hydrobromide','Descrizione di Eletriptan hydrobromide',71.88),(292,'Eligard','Descrizione di Eligard',15.26),(293,'Ellence','Descrizione di Ellence',87.17),(294,'Elmiron','Descrizione di Elmiron',38.57),(295,'Elspar','Descrizione di Elspar',99.32),(296,'Emadine','Descrizione di Emadine',10.32),(297,'Emcyt','Descrizione di Emcyt',42.69),(298,'Emedastine','Descrizione di Emedastine',22.54),(299,'Empirin','Descrizione di Empirin',58.21),(300,'Emsam','Descrizione di Emsam',72.89),(301,'Emtricitabine','Descrizione di Emtricitabine',0.76),(302,'Emtriva','Descrizione di Emtriva',14.29),(303,'Endocet','Descrizione di Endocet',24.68),(304,'Endometrin','Descrizione di Endometrin',87.92),(305,'Enflurane','Descrizione di Enflurane',15.25),(306,'Engerix-B','Descrizione di Engerix-B',6.55),(307,'Entereg','Descrizione di Entereg',35.34),(308,'Eovist','Descrizione di Eovist',66.49),(309,'Epinephrine','Descrizione di Epinephrine',75.41),(310,'Epipen','Descrizione di Epipen',96.12),(311,'Epirubicin hydrochloride','Descrizione di Epirubicin hydrochloride',2.45),(312,'Epivir','Descrizione di Epivir',81.60),(313,'Equetro','Descrizione di Equetro',18.18),(314,'Eraxis','Descrizione di Eraxis',38.53),(315,'Erbitux','Descrizione di Erbitux',62.39),(316,'Ergocalciferol','Descrizione di Ergocalciferol',25.23),(317,'Erlotinib','Descrizione di Erlotinib',63.79),(318,'Erythrocin Stearate','Descrizione di Erythrocin Stearate',60.96),(319,'Esomeprazole Sodium','Descrizione di Esomeprazole Sodium',67.87),(320,'Essential Amino Acids','Descrizione di Essential Amino Acids',26.29),(321,'Estrace','Descrizione di Estrace',26.49),(322,'Estradiol','Descrizione di Estradiol',75.37),(323,'Estradiol Acetate','Descrizione di Estradiol Acetate',99.66),(324,'Estradiol valerate','Descrizione di Estradiol valerate',30.49),(325,'Estratest','Descrizione di Estratest',2.93),(326,'Estropipate','Descrizione di Estropipate',32.95),(327,'Eszopiclone','Descrizione di Eszopiclone',33.97),(328,'Etanercept','Descrizione di Etanercept',5.16),(329,'Ethacrynic Acid','Descrizione di Ethacrynic Acid',83.86),(330,'Ethambutol','Descrizione di Ethambutol',62.05),(331,'Ethinyl Estradiol','Descrizione di Ethinyl Estradiol',45.88),(332,'Ethiodol','Descrizione di Ethiodol',63.82),(333,'Ethosuximide','Descrizione di Ethosuximide',25.55),(334,'Etidocaine HCl','Descrizione di Etidocaine HCl',58.34),(335,'Etidronate Disodium','Descrizione di Etidronate Disodium',82.14),(336,'Etopophos','Descrizione di Etopophos',18.01),(337,'Etrafon','Descrizione di Etrafon',14.16),(338,'Eulexin','Descrizione di Eulexin',7.71),(339,'Evista','Descrizione di Evista',58.86),(340,'Evoxac','Descrizione di Evoxac',23.63),(341,'Exelderm','Descrizione di Exelderm',44.13),(342,'Exjade','Descrizione di Exjade',44.55),(343,'Extavia','Descrizione di Extavia',12.85),(344,'Factor IX Complex','Descrizione di Factor IX Complex',1.53),(345,'Factrel','Descrizione di Factrel',10.12),(346,'Famciclovir','Descrizione di Famciclovir',9.08),(347,'Famotidine Injection','Descrizione di Famotidine Injection',19.32),(348,'Famvir','Descrizione di Famvir',10.45),(349,'Fansidar','Descrizione di Fansidar',44.13),(350,'Febuxostat','Descrizione di Febuxostat',78.56),(351,'Feridex I.V.','Descrizione di Feridex I.V.',84.21),(352,'Fesoterodine Fumarate Extended','Descrizione di Fesoterodine Fumarate Extended',96.58),(353,'Finacea','Descrizione di Finacea',37.46),(354,'Flector','Descrizione di Flector',80.82),(355,'Flonase','Descrizione di Flonase',5.81),(356,'Florinef','Descrizione di Florinef',42.44),(357,'Floxuridine','Descrizione di Floxuridine',32.96),(358,'Fluconazole','Descrizione di Fluconazole',3.22),(359,'Flucytosine','Descrizione di Flucytosine',72.29),(360,'Fludara','Descrizione di Fludara',22.61),(361,'Fludarabine Phosphate','Descrizione di Fludarabine Phosphate',56.35),(362,'Fludrocortisone','Descrizione di Fludrocortisone',5.50),(363,'Flumazenil','Descrizione di Flumazenil',12.73),(364,'FluMist','Descrizione di FluMist',5.66),(365,'Fluocinolone Acetonide','Descrizione di Fluocinolone Acetonide',21.44),(366,'Fluoroplex','Descrizione di Fluoroplex',17.59),(367,'Fluorouracil','Descrizione di Fluorouracil',63.92),(368,'Fluoxetine Hydrochloride','Descrizione di Fluoxetine Hydrochloride',65.39),(369,'Flurbiprofen','Descrizione di Flurbiprofen',73.53),(370,'Fluress','Descrizione di Fluress',1.24),(371,'Fluticasone Propionate','Descrizione di Fluticasone Propionate',62.54),(372,'Fluvirin','Descrizione di Fluvirin',96.10),(373,'FML','Descrizione di FML',48.45),(374,'Folic Acid','Descrizione di Folic Acid',7.49),(375,'Follitropin Alfa','Descrizione di Follitropin Alfa',32.86),(376,'Follitropin Beta','Descrizione di Follitropin Beta',84.13),(377,'Fomepizole','Descrizione di Fomepizole',45.74),(378,'Foradil Aerolizer','Descrizione di Foradil Aerolizer',81.22),(379,'Foradil Certihaler','Descrizione di Foradil Certihaler',22.68),(380,'Forane','Descrizione di Forane',41.18),(381,'Fosamax Plus D','Descrizione di Fosamax Plus D',88.87),(382,'Fosamprenavir Calcium','Descrizione di Fosamprenavir Calcium',5.11),(383,'Foscavir','Descrizione di Foscavir',80.58),(384,'Fosphenytoin Sodium','Descrizione di Fosphenytoin Sodium',74.91),(385,'Fragmin','Descrizione di Fragmin',3.02),(386,'Frovatriptan Succinate','Descrizione di Frovatriptan Succinate',37.25),(387,'Fulvestrant','Descrizione di Fulvestrant',65.77),(388,'Fungizone','Descrizione di Fungizone',45.14),(389,'Furadantin','Descrizione di Furadantin',94.14),(390,'Furosemide','Descrizione di Furosemide',59.24),(391,'Furoxone','Descrizione di Furoxone',94.34),(392,'Fuzeon','Descrizione di Fuzeon',59.74),(393,'Gabitril','Descrizione di Gabitril',44.72),(394,'Gadobenate Dimeglumine','Descrizione di Gadobenate Dimeglumine',2.59),(395,'Gadofosveset Trisodium','Descrizione di Gadofosveset Trisodium',19.33),(396,'Galsulfase','Descrizione di Galsulfase',81.70),(397,'Gamunex','Descrizione di Gamunex',75.87),(398,'Geocillin','Descrizione di Geocillin',83.97),(399,'Geodon','Descrizione di Geodon',76.18),(400,'Gleevec','Descrizione di Gleevec',82.77),(401,'Glucophage XR','Descrizione di Glucophage XR',78.73),(402,'Glucovance','Descrizione di Glucovance',23.70),(403,'Glyburide','Descrizione di Glyburide',18.63),(404,'Glycopyrrolate','Descrizione di Glycopyrrolate',27.68),(405,'Glynase','Descrizione di Glynase',72.92),(406,'Glyset','Descrizione di Glyset',40.85),(407,'Gold Sodium Thiomalate','Descrizione di Gold Sodium Thiomalate',32.02),(408,'Gonadorelin','Descrizione di Gonadorelin',85.80),(409,'Gonal-F','Descrizione di Gonal-F',93.13),(410,'Gonal-f RFF','Descrizione di Gonal-f RFF',77.27),(411,'Grifulvin V','Descrizione di Grifulvin V',49.02),(412,'Griseofulvin','Descrizione di Griseofulvin',41.99),(413,'Guanethidine Monosulfate','Descrizione di Guanethidine Monosulfate',6.27),(414,'Gynazole','Descrizione di Gynazole',27.25),(415,'Haemophilus b Conjugate Vaccine','Descrizione di Haemophilus b Conjugate Vaccine',16.43),(416,'Halcinonide','Descrizione di Halcinonide',20.24),(417,'Haldol','Descrizione di Haldol',11.23),(418,'Halobetasol Propionate','Descrizione di Halobetasol Propionate',0.72),(419,'Haloperidol','Descrizione di Haloperidol',31.61),(420,'Healon','Descrizione di Healon',42.81),(421,'HepaGam B','Descrizione di HepaGam B',11.03),(422,'Heparin Lock Flush','Descrizione di Heparin Lock Flush',75.32),(423,'HepatAmine','Descrizione di HepatAmine',76.05),(424,'Hepatitis A Vaccine, Inactivated','Descrizione di Hepatitis A Vaccine, Inactivated',76.93),(425,'Hepatitis B Immune Globulin','Descrizione di Hepatitis B Immune Globulin',28.25),(426,'Hepflush-10','Descrizione di Hepflush-10',31.31),(427,'Herceptin','Descrizione di Herceptin',85.92),(428,'Hexachlorophene','Descrizione di Hexachlorophene',2.42),(429,'HibTITER','Descrizione di HibTITER',73.22),(430,'Hivid','Descrizione di Hivid',13.86),(431,'Human Secretin','Descrizione di Human Secretin',31.64),(432,'Humira','Descrizione di Humira',80.00),(433,'Humulin N','Descrizione di Humulin N',92.87),(434,'Hyalgan','Descrizione di Hyalgan',84.60),(435,'Hydrocodone Bitartrate and Acetaminophen','Descrizione di Hydrocodone Bitartrate and Acetaminophen',25.13),(436,'Hydroxyethyl Starch','Descrizione di Hydroxyethyl Starch',10.74),(437,'Hylenex','Descrizione di Hylenex',43.70),(438,'Hyoscyamine','Descrizione di Hyoscyamine',92.70),(439,'Hytrin','Descrizione di Hytrin',3.35),(440,'Ibuprofen Lysine','Descrizione di Ibuprofen Lysine',9.33),(441,'Idamycin','Descrizione di Idamycin',98.11),(442,'Idamycin PFS','Descrizione di Idamycin PFS',58.60),(443,'Ifosfamide','Descrizione di Ifosfamide',89.96),(444,'Iloperidone','Descrizione di Iloperidone',47.67),(445,'Imipramine','Descrizione di Imipramine',73.85),(446,'Imiquimod','Descrizione di Imiquimod',26.50),(447,'Imitrex','Descrizione di Imitrex',2.40),(448,'Immune Globulin','Descrizione di Immune Globulin',37.94),(449,'Immune Globulin Intravenous','Descrizione di Immune Globulin Intravenous',27.95),(450,'Implanon','Descrizione di Implanon',17.24),(451,'Inderal LA','Descrizione di Inderal LA',81.19),(452,'Indigo Carmine','Descrizione di Indigo Carmine',22.25),(453,'InnoPran XL','Descrizione di InnoPran XL',66.76),(454,'Insulin','Descrizione di Insulin',17.94),(455,'Insulin Aspart','Descrizione di Insulin Aspart',27.58),(456,'Intelence','Descrizione di Intelence',64.02),(457,'Intralipid 20%','Descrizione di Intralipid 20%',74.71),(458,'Intuniv','Descrizione di Intuniv',25.66),(459,'Invanz','Descrizione di Invanz',47.78),(460,'Invega','Descrizione di Invega',26.93),(461,'Inversine','Descrizione di Inversine',39.51),(462,'Ionamin','Descrizione di Ionamin',55.84),(463,'Irinotecan Hydrochloride','Descrizione di Irinotecan Hydrochloride',11.18),(464,'Isentress','Descrizione di Isentress',85.64),(465,'Ismo','Descrizione di Ismo',12.19),(466,'Isocarboxazid','Descrizione di Isocarboxazid',32.52),(467,'Isoptin SR','Descrizione di Isoptin SR',29.00),(468,'Isopto Carpine','Descrizione di Isopto Carpine',81.87),(469,'Isopto Hyoscine','Descrizione di Isopto Hyoscine',11.60),(470,'Istalol','Descrizione di Istalol',20.26),(471,'Isuprel','Descrizione di Isuprel',91.10),(472,'Ixempra','Descrizione di Ixempra',48.57),(473,'Jalyn','Descrizione di Jalyn',22.70),(474,'Janumet','Descrizione di Janumet',4.15),(475,'Je-Vax','Descrizione di Je-Vax',96.76),(476,'K-LOR','Descrizione di K-LOR',3.27),(477,'Kaletra','Descrizione di Kaletra',21.43),(478,'Kariva','Descrizione di Kariva',28.58),(479,'Kenalog','Descrizione di Kenalog',72.64),(480,'Kinlytic','Descrizione di Kinlytic',52.09),(481,'Klonopin','Descrizione di Klonopin',79.82),(482,'Kuvan','Descrizione di Kuvan',71.86),(483,'Kytril','Descrizione di Kytril',96.65),(484,'Labetalol','Descrizione di Labetalol',35.87),(485,'lacosamide','Descrizione di lacosamide',96.77),(486,'Lamisil','Descrizione di Lamisil',45.74),(487,'Lamivudine / Zidovudine','Descrizione di Lamivudine / Zidovudine',27.25),(488,'Latanoprost','Descrizione di Latanoprost',57.27),(489,'Letairis','Descrizione di Letairis',57.29),(490,'Letrozole','Descrizione di Letrozole',20.28),(491,'Leuprolide Acetate','Descrizione di Leuprolide Acetate',55.23),(492,'Leustatin','Descrizione di Leustatin',86.20),(493,'Levalbuterol','Descrizione di Levalbuterol',70.02),(494,'Levaquin','Descrizione di Levaquin',50.62),(495,'Levemir','Descrizione di Levemir',2.23),(496,'Levo-T','Descrizione di Levo-T',46.96),(497,'Levocabastine','Descrizione di Levocabastine',38.37),(498,'Levofloxacin','Descrizione di Levofloxacin',33.61),(499,'Levonorgestrel','Descrizione di Levonorgestrel',2.95),(500,'Levonorgestrel and Ethinyl Estradiol','Descrizione di Levonorgestrel and Ethinyl Estradiol',26.25),(501,'Levonorgestrel Implants','Descrizione di Levonorgestrel Implants',13.64),(502,'Levonorgestrel, Ethinyl Estradiol','Descrizione di Levonorgestrel, Ethinyl Estradiol',83.60),(503,'Lexapro','Descrizione di Lexapro',63.02),(504,'Lexiscan','Descrizione di Lexiscan',82.16),(505,'Lexxel','Descrizione di Lexxel',51.30),(506,'Librium','Descrizione di Librium',4.26),(507,'Lidex','Descrizione di Lidex',91.11),(508,'Lidoderm','Descrizione di Lidoderm',8.71),(509,'Linezolid','Descrizione di Linezolid',65.11),(510,'Lipofen','Descrizione di Lipofen',56.47),(511,'Liposyn II','Descrizione di Liposyn II',12.53),(512,'Liraglutide','Descrizione di Liraglutide',16.20),(513,'Lisinopril and Hydrochlorothiazide','Descrizione di Lisinopril and Hydrochlorothiazide',98.90),(514,'Locoid','Descrizione di Locoid',50.24),(515,'Lodine','Descrizione di Lodine',2.88),(516,'Loperamide Hcl','Descrizione di Loperamide Hcl',18.63),(517,'Lopid','Descrizione di Lopid',50.40),(518,'Loprox Gel','Descrizione di Loprox Gel',9.51),(519,'Loracarbef','Descrizione di Loracarbef',87.62),(520,'Lortab','Descrizione di Lortab',39.29),(521,'Lotemax','Descrizione di Lotemax',10.00),(522,'Lotensin','Descrizione di Lotensin',78.13),(523,'Lotronex','Descrizione di Lotronex',90.58),(524,'Lovenox','Descrizione di Lovenox',76.78),(525,'Loxapine','Descrizione di Loxapine',84.65),(526,'Loxitane','Descrizione di Loxitane',31.07),(527,'Lucentis','Descrizione di Lucentis',15.77),(528,'Luvox CR','Descrizione di Luvox CR',13.00),(529,'Lybrel','Descrizione di Lybrel',61.58),(530,'M-M-R','Descrizione di M-M-R',65.39),(531,'Malarone','Descrizione di Malarone',24.03),(532,'Malathion','Descrizione di Malathion',28.60),(533,'Mandol','Descrizione di Mandol',94.57),(534,'Mangafodipir','Descrizione di Mangafodipir',24.24),(535,'Maraviroc','Descrizione di Maraviroc',65.77),(536,'Marinol','Descrizione di Marinol',52.08),(537,'Maxitrol','Descrizione di Maxitrol',12.13),(538,'Mecasermin','Descrizione di Mecasermin',49.87),(539,'Meclofenamate','Descrizione di Meclofenamate',99.01),(540,'Mefloquine','Descrizione di Mefloquine',29.50),(541,'Melphalan','Descrizione di Melphalan',45.60),(542,'Menactra','Descrizione di Menactra',25.28),(543,'Menest','Descrizione di Menest',30.93),(544,'Menotropins','Descrizione di Menotropins',74.84),(545,'Mephobarbital','Descrizione di Mephobarbital',95.05),(546,'Mequinol and Tretinoin','Descrizione di Mequinol and Tretinoin',66.40),(547,'Meropenem','Descrizione di Meropenem',12.11),(548,'Merrem I.V.','Descrizione di Merrem I.V.',88.04),(549,'Mesalamine','Descrizione di Mesalamine',29.35),(550,'Mesna','Descrizione di Mesna',24.56),(551,'Mestinon','Descrizione di Mestinon',61.72),(552,'Metadate ER','Descrizione di Metadate ER',94.50),(553,'Metaglip','Descrizione di Metaglip',89.23),(554,'Metaproterenol Sulfate','Descrizione di Metaproterenol Sulfate',45.85),(555,'Metaxalone','Descrizione di Metaxalone',6.56),(556,'Metformin Hcl','Descrizione di Metformin Hcl',33.16),(557,'Methadone Hydrochloride','Descrizione di Methadone Hydrochloride',17.26),(558,'Methadose Oral Concentrate','Descrizione di Methadose Oral Concentrate',94.57),(559,'Methazolamide','Descrizione di Methazolamide',86.26),(560,'Methenamine Hippurate','Descrizione di Methenamine Hippurate',34.57),(561,'Methergine','Descrizione di Methergine',94.37),(562,'Methohexital Sodium','Descrizione di Methohexital Sodium',33.71),(563,'Methyclothiazide','Descrizione di Methyclothiazide',39.69),(564,'Methyldopa','Descrizione di Methyldopa',94.61),(565,'Methylene Blue','Descrizione di Methylene Blue',29.96),(566,'Methylergonovine Maleate','Descrizione di Methylergonovine Maleate',31.22),(567,'Methylin','Descrizione di Methylin',97.17),(568,'Methyltestosterone','Descrizione di Methyltestosterone',2.12),(569,'Metipranolol','Descrizione di Metipranolol',28.17),(570,'Metoclopramide','Descrizione di Metoclopramide',87.96),(571,'Metoprolol Tartrate','Descrizione di Metoprolol Tartrate',16.85),(572,'MetroLotion','Descrizione di MetroLotion',41.41),(573,'Metyrapone','Descrizione di Metyrapone',45.23),(574,'Metyrosine','Descrizione di Metyrosine',42.29),(575,'Miacalcin','Descrizione di Miacalcin',43.29),(576,'Micro-K','Descrizione di Micro-K',46.65),(577,'Micronase','Descrizione di Micronase',66.59),(578,'Micronized Glyburide','Descrizione di Micronized Glyburide',90.32),(579,'Midazolam','Descrizione di Midazolam',23.96),(580,'Midodrine Hydrochloride','Descrizione di Midodrine Hydrochloride',99.55),(581,'Milrinone','Descrizione di Milrinone',21.53),(582,'Minocin','Descrizione di Minocin',70.62),(583,'Minocycline','Descrizione di Minocycline',36.84),(584,'Minoxidil','Descrizione di Minoxidil',98.34),(585,'Miochol-E','Descrizione di Miochol-E',67.54),(586,'Miostat','Descrizione di Miostat',70.72),(587,'Mitomycin','Descrizione di Mitomycin',15.57),(588,'Mobic','Descrizione di Mobic',44.69),(589,'Modafinil','Descrizione di Modafinil',26.32),(590,'Monistat','Descrizione di Monistat',30.63),(591,'Monistat-Derm','Descrizione di Monistat-Derm',27.07),(592,'Morrhuate Sodium','Descrizione di Morrhuate Sodium',25.83),(593,'Motrin','Descrizione di Motrin',10.11),(594,'Moxatag','Descrizione di Moxatag',17.35),(595,'Mozobil','Descrizione di Mozobil',1.67),(596,'Multaq','Descrizione di Multaq',29.48),(597,'Multi Vitamin','Descrizione di Multi Vitamin',47.75),(598,'Multihance','Descrizione di Multihance',30.38),(599,'Mustargen','Descrizione di Mustargen',23.23),(600,'Mutamycin','Descrizione di Mutamycin',22.42),(601,'Myambutol','Descrizione di Myambutol',38.54),(602,'Mycamine','Descrizione di Mycamine',66.11),(603,'Mycelex','Descrizione di Mycelex',28.41),(604,'Mycophenolic Acid','Descrizione di Mycophenolic Acid',2.75),(605,'Myfortic','Descrizione di Myfortic',15.59),(606,'Mykrox','Descrizione di Mykrox',79.25),(607,'Myobloc','Descrizione di Myobloc',33.21),(608,'Myochrysine','Descrizione di Myochrysine',79.70),(609,'Nafcillin Sodium','Descrizione di Nafcillin Sodium',17.26),(610,'Naftifine Hcl','Descrizione di Naftifine Hcl',20.34),(611,'Nalmefene Hydrochloride','Descrizione di Nalmefene Hydrochloride',53.05),(612,'Naltrexone','Descrizione di Naltrexone',18.83),(613,'Naproxen','Descrizione di Naproxen',39.50),(614,'Nascobal','Descrizione di Nascobal',74.98),(615,'Natazia','Descrizione di Natazia',23.79),(616,'Natrecor','Descrizione di Natrecor',28.01),(617,'Navelbine','Descrizione di Navelbine',22.93),(618,'Nebcin','Descrizione di Nebcin',28.34),(619,'Nebivolol Tablets','Descrizione di Nebivolol Tablets',17.37),(620,'Nedocromil','Descrizione di Nedocromil',66.34),(621,'Nelarabine','Descrizione di Nelarabine',56.56),(622,'Nelfinavir Mesylate','Descrizione di Nelfinavir Mesylate',20.93),(623,'NeoProfen','Descrizione di NeoProfen',2.76),(624,'Neostigmine','Descrizione di Neostigmine',58.05),(625,'Nephramine','Descrizione di Nephramine',70.62),(626,'Nesacaine','Descrizione di Nesacaine',6.48),(627,'Neulasta','Descrizione di Neulasta',59.81),(628,'Nexavar','Descrizione di Nexavar',4.00),(629,'Niaspan','Descrizione di Niaspan',45.13),(630,'Nicotrol','Descrizione di Nicotrol',67.41),(631,'Nicotrol NS','Descrizione di Nicotrol NS',99.55),(632,'Nilandron','Descrizione di Nilandron',49.55),(633,'Nilotinib Capsules','Descrizione di Nilotinib Capsules',32.42),(634,'Nimbex','Descrizione di Nimbex',15.62),(635,'Nimotop','Descrizione di Nimotop',77.11),(636,'Nitroglycerin','Descrizione di Nitroglycerin',9.77),(637,'NitroMist','Descrizione di NitroMist',5.24),(638,'Nizatidine','Descrizione di Nizatidine',27.78),(639,'Nizoral','Descrizione di Nizoral',18.52),(640,'Noctec','Descrizione di Noctec',85.43),(641,'Nor-QD','Descrizione di Nor-QD',83.96),(642,'Norethindrone and Ethinyl Estradiol','Descrizione di Norethindrone and Ethinyl Estradiol',27.73),(643,'Noritate','Descrizione di Noritate',1.40),(644,'Nortriptyline Hydrochloride','Descrizione di Nortriptyline Hydrochloride',94.13),(645,'Norvasc','Descrizione di Norvasc',9.75),(646,'NovoLog Mix 70/30','Descrizione di NovoLog Mix 70/30',1.72),(647,'Novoseven','Descrizione di Novoseven',29.18),(648,'Numorphan','Descrizione di Numorphan',7.10),(649,'Nutropin AQ','Descrizione di Nutropin AQ',11.17),(650,'Nutropin Depot','Descrizione di Nutropin Depot',50.32),(651,'Nydrazid','Descrizione di Nydrazid',82.12),(652,'Omeprazole','Descrizione di Omeprazole',7.95),(653,'Omnaris','Descrizione di Omnaris',32.69),(654,'Opana','Descrizione di Opana',39.31),(655,'Opticrom','Descrizione di Opticrom',1.40),(656,'OptiMARK','Descrizione di OptiMARK',65.88),(657,'Optipranolol','Descrizione di Optipranolol',87.85),(658,'Oracea','Descrizione di Oracea',91.90),(659,'Oraqix','Descrizione di Oraqix',95.97),(660,'Orfadin','Descrizione di Orfadin',26.89),(661,'Orlaam','Descrizione di Orlaam',60.90),(662,'Orlistat','Descrizione di Orlistat',52.45),(663,'Orudis','Descrizione di Orudis',94.53),(664,'Ovcon','Descrizione di Ovcon',94.14),(665,'Ovide','Descrizione di Ovide',87.51),(666,'Oxandrolone','Descrizione di Oxandrolone',68.40),(667,'Oxaprozin','Descrizione di Oxaprozin',21.44),(668,'Oxistat','Descrizione di Oxistat',13.58),(669,'Oxsoralen-Ultra','Descrizione di Oxsoralen-Ultra',4.35),(670,'Oxycodone HCl','Descrizione di Oxycodone HCl',88.59),(671,'Oxycodone Hydrochloride','Descrizione di Oxycodone Hydrochloride',12.92),(672,'Oxycontin','Descrizione di Oxycontin',77.05),(673,'Oxymetholone','Descrizione di Oxymetholone',53.64),(674,'Oxymorphone','Descrizione di Oxymorphone',22.81),(675,'Oxytetracycline','Descrizione di Oxytetracycline',86.03),(676,'Paclitaxel','Descrizione di Paclitaxel',39.29),(677,'Palifermin','Descrizione di Palifermin',38.75),(678,'Paliperidone','Descrizione di Paliperidone',7.08),(679,'Palonosetron hydrochloride','Descrizione di Palonosetron hydrochloride',93.92),(680,'Panhematin','Descrizione di Panhematin',60.97),(681,'Pantoprazole','Descrizione di Pantoprazole',76.49),(682,'Parafon Forte','Descrizione di Parafon Forte',96.56),(683,'Parnate','Descrizione di Parnate',56.61),(684,'Paser','Descrizione di Paser',17.27),(685,'Pataday','Descrizione di Pataday',65.67),(686,'Pazopanib','Descrizione di Pazopanib',96.41),(687,'Pediapred','Descrizione di Pediapred',62.29),(688,'PEG 3350','Descrizione di PEG 3350',13.40),(689,'Pegfilgrastim','Descrizione di Pegfilgrastim',79.13),(690,'Pemirolast Potassium','Descrizione di Pemirolast Potassium',92.74),(691,'Penciclovir','Descrizione di Penciclovir',7.01),(692,'Penicillamine','Descrizione di Penicillamine',61.77),(693,'Penlac','Descrizione di Penlac',3.15),(694,'Pentetate Zinc Trisodium','Descrizione di Pentetate Zinc Trisodium',87.83),(695,'Pentobarbital','Descrizione di Pentobarbital',83.13),(696,'Pentoxifylline','Descrizione di Pentoxifylline',40.92),(697,'Perflutren','Descrizione di Perflutren',59.24),(698,'Perindopril Erbumine','Descrizione di Perindopril Erbumine',57.01),(699,'Permax','Descrizione di Permax',34.92),(700,'Persantine','Descrizione di Persantine',58.59),(701,'Pfizerpen','Descrizione di Pfizerpen',27.70),(702,'Phenazopyridine','Descrizione di Phenazopyridine',65.28),(703,'Phenelzine','Descrizione di Phenelzine',81.27),(704,'Phenobarbital','Descrizione di Phenobarbital',41.84),(705,'Phenoxybenzamine','Descrizione di Phenoxybenzamine',1.75),(706,'Phenylephrine HCl','Descrizione di Phenylephrine HCl',88.86),(707,'Phenylephrine Hydrochloride','Descrizione di Phenylephrine Hydrochloride',55.98),(708,'Phenytoin','Descrizione di Phenytoin',50.70),(709,'Phosphate','Descrizione di Phosphate',63.87),(710,'Photofrin','Descrizione di Photofrin',39.47),(711,'Pilocarpine Hydrochloride','Descrizione di Pilocarpine Hydrochloride',27.04),(712,'Pilopine HS','Descrizione di Pilopine HS',6.03),(713,'Pindolol','Descrizione di Pindolol',13.21),(714,'Pipracil','Descrizione di Pipracil',27.63),(715,'Piroxicam','Descrizione di Piroxicam',58.06),(716,'Plaquenil','Descrizione di Plaquenil',24.63),(717,'PlasmaLyte A','Descrizione di PlasmaLyte A',91.10),(718,'Plavix','Descrizione di Plavix',22.71),(719,'Plenaxis','Descrizione di Plenaxis',75.89),(720,'Pletal','Descrizione di Pletal',83.40),(721,'Pneumovax','Descrizione di Pneumovax',22.64),(722,'Podophyllin','Descrizione di Podophyllin',56.42),(723,'Polidocanol','Descrizione di Polidocanol',77.19),(724,'Polyethylene Glycol 3350','Descrizione di Polyethylene Glycol 3350',10.13),(725,'Polythiazide','Descrizione di Polythiazide',76.91),(726,'Pramipexole','Descrizione di Pramipexole',68.04),(727,'Pred-G','Descrizione di Pred-G',19.18),(728,'Prednicarbate','Descrizione di Prednicarbate',93.32),(729,'Prednisolone Acetate','Descrizione di Prednisolone Acetate',8.50),(730,'Prednisone','Descrizione di Prednisone',53.05),(731,'Prefest','Descrizione di Prefest',50.75),(732,'Pregnyl','Descrizione di Pregnyl',16.39),(733,'Premarin','Descrizione di Premarin',4.03),(734,'Prepidil','Descrizione di Prepidil',15.22),(735,'Prevpac','Descrizione di Prevpac',11.98),(736,'Priftin','Descrizione di Priftin',13.47),(737,'Primacor','Descrizione di Primacor',36.84),(738,'Primaquine','Descrizione di Primaquine',37.48),(739,'Primidone','Descrizione di Primidone',30.71),(740,'Prinivil','Descrizione di Prinivil',79.64),(741,'Prinzide','Descrizione di Prinzide',30.13),(742,'Pristiq','Descrizione di Pristiq',11.75),(743,'Procainamide','Descrizione di Procainamide',16.45),(744,'Procalamine','Descrizione di Procalamine',88.12),(745,'Prochlorperazine Maleate','Descrizione di Prochlorperazine Maleate',76.46),(746,'ProHance','Descrizione di ProHance',87.78),(747,'Proleukin','Descrizione di Proleukin',18.69),(748,'Prolixin','Descrizione di Prolixin',53.62),(749,'Promethazine HCl','Descrizione di Promethazine HCl',86.19),(750,'Promethazine Hydrochloride','Descrizione di Promethazine Hydrochloride',40.85),(751,'Prometrium','Descrizione di Prometrium',83.89),(752,'Propecia','Descrizione di Propecia',60.44),(753,'Proquin XR','Descrizione di Proquin XR',29.65),(754,'Prostin VR Pediatric','Descrizione di Prostin VR Pediatric',86.40),(755,'Protein C Concentrate','Descrizione di Protein C Concentrate',33.45),(756,'Protopic','Descrizione di Protopic',56.08),(757,'Protriptyline Hydrochloride','Descrizione di Protriptyline Hydrochloride',84.18),(758,'Proventil HFA','Descrizione di Proventil HFA',79.70),(759,'Provisc','Descrizione di Provisc',97.01),(760,'Provocholine','Descrizione di Provocholine',84.23),(761,'Pulmicort Flexhaler','Descrizione di Pulmicort Flexhaler',12.32),(762,'Pylera','Descrizione di Pylera',45.72),(763,'Pyrazinamide','Descrizione di Pyrazinamide',72.52),(764,'Pyridium','Descrizione di Pyridium',51.87),(765,'Pyridostigmine','Descrizione di Pyridostigmine',86.70),(766,'Qualaquin','Descrizione di Qualaquin',98.63),(767,'Quazepam','Descrizione di Quazepam',1.21),(768,'Quinidine Sulfate','Descrizione di Quinidine Sulfate',72.03),(769,'Quixin','Descrizione di Quixin',45.23),(770,'Rabies Vaccine','Descrizione di Rabies Vaccine',36.27),(771,'Raltegravir','Descrizione di Raltegravir',17.00),(772,'Ranexa','Descrizione di Ranexa',70.69),(773,'Ranitidine Hcl','Descrizione di Ranitidine Hcl',41.15),(774,'Rapamune','Descrizione di Rapamune',56.65),(775,'Rasagiline','Descrizione di Rasagiline',1.28),(776,'Raxar','Descrizione di Raxar',16.43),(777,'Rebetol','Descrizione di Rebetol',54.47),(778,'Remicade','Descrizione di Remicade',21.88),(779,'Remifentanil','Descrizione di Remifentanil',30.43),(780,'Renese','Descrizione di Renese',27.37),(781,'ReoPro','Descrizione di ReoPro',25.09),(782,'Rescriptor','Descrizione di Rescriptor',65.63),(783,'Rescula','Descrizione di Rescula',51.49),(784,'Revatio','Descrizione di Revatio',47.81),(785,'Revex','Descrizione di Revex',16.88),(786,'Revia','Descrizione di Revia',14.42),(787,'Reyataz','Descrizione di Reyataz',7.08),(788,'Rezulin','Descrizione di Rezulin',21.60),(789,'Rhinocort Aqua','Descrizione di Rhinocort Aqua',13.21),(790,'Rhogam Ultra-Filtered Plus','Descrizione di Rhogam Ultra-Filtered Plus',18.58),(791,'RiaSTAP','Descrizione di RiaSTAP',0.54),(792,'Rifamate','Descrizione di Rifamate',9.88),(793,'Riomet','Descrizione di Riomet',42.46),(794,'Risperidone','Descrizione di Risperidone',7.90),(795,'Ritalin','Descrizione di Ritalin',80.49),(796,'Rituximab','Descrizione di Rituximab',29.43),(797,'Rivastigmine Tartrate','Descrizione di Rivastigmine Tartrate',15.30),(798,'Robinul','Descrizione di Robinul',57.20),(799,'Rosiglitazone Maleate','Descrizione di Rosiglitazone Maleate',8.48),(800,'Rotarix','Descrizione di Rotarix',71.67),(801,'RotaTeq','Descrizione di RotaTeq',46.36),(802,'Roxicet','Descrizione di Roxicet',8.83),(803,'Roxicodone','Descrizione di Roxicodone',81.35),(804,'Ryzolt','Descrizione di Ryzolt',23.26),(805,'Sabril','Descrizione di Sabril',52.85),(806,'Sacrosidase','Descrizione di Sacrosidase',71.38),(807,'Samsca','Descrizione di Samsca',99.53),(808,'Sanctura','Descrizione di Sanctura',57.29),(809,'Santyl','Descrizione di Santyl',13.24),(810,'Saphris','Descrizione di Saphris',58.48),(811,'Scopolamine','Descrizione di Scopolamine',0.23),(812,'Seasonale','Descrizione di Seasonale',34.59),(813,'Selegiline Hydrochloride','Descrizione di Selegiline Hydrochloride',23.57),(814,'Selsun','Descrizione di Selsun',48.66),(815,'Septra','Descrizione di Septra',50.44),(816,'Serax','Descrizione di Serax',50.55),(817,'Sertraline Hcl','Descrizione di Sertraline Hcl',34.18),(818,'Serzone','Descrizione di Serzone',28.26),(819,'Sevoflurane','Descrizione di Sevoflurane',55.11),(820,'Sibutramine Hydrochloride Monohydrate','Descrizione di Sibutramine Hydrochloride Monohydrate',11.25),(821,'Silenor','Descrizione di Silenor',4.55),(822,'Simponi','Descrizione di Simponi',11.01),(823,'Sirolimus','Descrizione di Sirolimus',26.49),(824,'Sitagliptin Metformin HCL','Descrizione di Sitagliptin Metformin HCL',95.96),(825,'Slow-K','Descrizione di Slow-K',86.84),(826,'Sodium Bicarbonate','Descrizione di Sodium Bicarbonate',96.15),(827,'Sodium ferric gluconate','Descrizione di Sodium ferric gluconate',80.64),(828,'Sodium Iodide I 131','Descrizione di Sodium Iodide I 131',34.42),(829,'Sodium Polystyrene Sulfonate','Descrizione di Sodium Polystyrene Sulfonate',81.75),(830,'Sodium Sulfacetamide','Descrizione di Sodium Sulfacetamide',80.13),(831,'Soma Compound','Descrizione di Soma Compound',62.42),(832,'Somatrem','Descrizione di Somatrem',12.96),(833,'Somatropin','Descrizione di Somatropin',20.48),(834,'Sonata','Descrizione di Sonata',51.72),(835,'Soriatane','Descrizione di Soriatane',5.26),(836,'Sotradecol','Descrizione di Sotradecol',47.06),(837,'Spiriva','Descrizione di Spiriva',62.73),(838,'Sporanox','Descrizione di Sporanox',45.29),(839,'Sprix','Descrizione di Sprix',25.04),(840,'Sprycel','Descrizione di Sprycel',68.05),(841,'Stalevo','Descrizione di Stalevo',7.26),(842,'Starlix','Descrizione di Starlix',99.12),(843,'Stavudine','Descrizione di Stavudine',94.75),(844,'Streptokinase','Descrizione di Streptokinase',1.93),(845,'Strontium-89','Descrizione di Strontium-89',66.65),(846,'Suboxone','Descrizione di Suboxone',61.36),(847,'Succimer','Descrizione di Succimer',48.36),(848,'Succinylcholine Chloride','Descrizione di Succinylcholine Chloride',92.41),(849,'Sucralfate','Descrizione di Sucralfate',9.14),(850,'Sulfamylon','Descrizione di Sulfamylon',60.94),(851,'Sunitinib Malate','Descrizione di Sunitinib Malate',83.56),(852,'Sutent','Descrizione di Sutent',67.52),(853,'Synthroid','Descrizione di Synthroid',40.36),(854,'Synvisc','Descrizione di Synvisc',9.38),(855,'Syprine','Descrizione di Syprine',5.82),(856,'Tacrolimus','Descrizione di Tacrolimus',14.55),(857,'Talacen','Descrizione di Talacen',11.15),(858,'Talwin Nx','Descrizione di Talwin Nx',12.31),(859,'Tamiflu','Descrizione di Tamiflu',83.30),(860,'Tamoxifen Citrate','Descrizione di Tamoxifen Citrate',79.41),(861,'Tapazole','Descrizione di Tapazole',47.25),(862,'Targretin','Descrizione di Targretin',21.11),(863,'Tasmar','Descrizione di Tasmar',27.37),(864,'Tegretol','Descrizione di Tegretol',42.86),(865,'Tekturna HCT','Descrizione di Tekturna HCT',97.90),(866,'Telavancin','Descrizione di Telavancin',17.50),(867,'Telbivudine','Descrizione di Telbivudine',57.62),(868,'Telmisartan','Descrizione di Telmisartan',17.34),(869,'Temovate Scalp','Descrizione di Temovate Scalp',9.93),(870,'Temozolomide','Descrizione di Temozolomide',4.53),(871,'Temsirolimus','Descrizione di Temsirolimus',15.16),(872,'Teniposide','Descrizione di Teniposide',35.52),(873,'Terazol 3, Terazol 7','Descrizione di Terazol 3, Terazol 7',41.08),(874,'Tessalon','Descrizione di Tessalon',73.62),(875,'Testolactone','Descrizione di Testolactone',23.33),(876,'Testred','Descrizione di Testred',55.54),(877,'Teveten HCT','Descrizione di Teveten HCT',64.03),(878,'Theracys','Descrizione di Theracys',67.34),(879,'Thiabendazole','Descrizione di Thiabendazole',64.03),(880,'Thiethylperazine','Descrizione di Thiethylperazine',43.56),(881,'Thiopental Sodium','Descrizione di Thiopental Sodium',2.48),(882,'Thioridazine','Descrizione di Thioridazine',12.24),(883,'Thiothixene Hcl','Descrizione di Thiothixene Hcl',24.17),(884,'Thrombin','Descrizione di Thrombin',43.13),(885,'Thyrolar','Descrizione di Thyrolar',72.09),(886,'Thyrotropin Alfa','Descrizione di Thyrotropin Alfa',41.28),(887,'Tiazac','Descrizione di Tiazac',93.00),(888,'Ticarcillin','Descrizione di Ticarcillin',11.80),(889,'Tinzaparin','Descrizione di Tinzaparin',14.18),(890,'Tirosint','Descrizione di Tirosint',69.58),(891,'Tizanidine','Descrizione di Tizanidine',41.50),(892,'Tobrex','Descrizione di Tobrex',42.55),(893,'Tofranil-PM','Descrizione di Tofranil-PM',14.61),(894,'Tolazamide','Descrizione di Tolazamide',53.64),(895,'Tolmetin Sodium','Descrizione di Tolmetin Sodium',20.03),(896,'Tonocard','Descrizione di Tonocard',37.76),(897,'Topicort','Descrizione di Topicort',97.43),(898,'Topiramate','Descrizione di Topiramate',70.09),(899,'Topotecan Hydrochloride','Descrizione di Topotecan Hydrochloride',50.02),(900,'Toradol','Descrizione di Toradol',36.61),(901,'Torsemide','Descrizione di Torsemide',85.89),(902,'Toviaz','Descrizione di Toviaz',50.48),(903,'Tramadol Hcl','Descrizione di Tramadol Hcl',13.82),(904,'Tranxene','Descrizione di Tranxene',37.53),(905,'Trastuzumab','Descrizione di Trastuzumab',7.74),(906,'Trasylol','Descrizione di Trasylol',23.20),(907,'Tretinoin','Descrizione di Tretinoin',55.02),(908,'Trexall','Descrizione di Trexall',70.23),(909,'Tri-Sprintec','Descrizione di Tri-Sprintec',39.31),(910,'Triamcinolone Acetonide','Descrizione di Triamcinolone Acetonide',94.69),(911,'Triazolam','Descrizione di Triazolam',39.78),(912,'Tribenzor','Descrizione di Tribenzor',3.59),(913,'Trientine','Descrizione di Trientine',91.08),(914,'Trihexyphenidyl','Descrizione di Trihexyphenidyl',27.19),(915,'Trilipix','Descrizione di Trilipix',40.71),(916,'Trilisate','Descrizione di Trilisate',1.03),(917,'Trimethadione','Descrizione di Trimethadione',20.45),(918,'Trimethoprim','Descrizione di Trimethoprim',98.81),(919,'Trimethoprim and Sulfamethoxazole','Descrizione di Trimethoprim and Sulfamethoxazole',66.04),(920,'Trimetrexate Glucuronate','Descrizione di Trimetrexate Glucuronate',24.92),(921,'Trizivir','Descrizione di Trizivir',27.85),(922,'Trovafloxacin','Descrizione di Trovafloxacin',54.13),(923,'Trovan','Descrizione di Trovan',67.98),(924,'Trusopt','Descrizione di Trusopt',56.89),(925,'Trypan Blue','Descrizione di Trypan Blue',28.22),(926,'Tussionex','Descrizione di Tussionex',60.38),(927,'Tysabri','Descrizione di Tysabri',91.37),(928,'Tyvaso','Descrizione di Tyvaso',65.10),(929,'Uloric','Descrizione di Uloric',15.61),(930,'Ultiva','Descrizione di Ultiva',63.34),(931,'Ultram','Descrizione di Ultram',26.08),(932,'Ultrase','Descrizione di Ultrase',0.61),(933,'Ultravate','Descrizione di Ultravate',40.59),(934,'Unasyn','Descrizione di Unasyn',65.93),(935,'Urex','Descrizione di Urex',16.15),(936,'Ursodiol','Descrizione di Ursodiol',53.69),(937,'Vagistat-1','Descrizione di Vagistat-1',50.37),(938,'Valacyclovir Hydrochloride','Descrizione di Valacyclovir Hydrochloride',89.69),(939,'Valganciclovir Hcl','Descrizione di Valganciclovir Hcl',1.58),(940,'Valium','Descrizione di Valium',48.00),(941,'Valproic Acid','Descrizione di Valproic Acid',40.71),(942,'Valsartan and Hydrochlorothiazide','Descrizione di Valsartan and Hydrochlorothiazide',63.64),(943,'Vancomycin Hydrochloride','Descrizione di Vancomycin Hydrochloride',52.17),(944,'Vaprisol','Descrizione di Vaprisol',63.15),(945,'Vasocidin','Descrizione di Vasocidin',46.55),(946,'Vasotec','Descrizione di Vasotec',79.15),(947,'Vasovist','Descrizione di Vasovist',84.30),(948,'Vectibix','Descrizione di Vectibix',31.39),(949,'Vectical','Descrizione di Vectical',3.12),(950,'Velosulin','Descrizione di Velosulin',93.88),(951,'Veltin','Descrizione di Veltin',81.82),(952,'Venlafaxine Hydrochloride','Descrizione di Venlafaxine Hydrochloride',20.54),(953,'Veramyst','Descrizione di Veramyst',61.85),(954,'Vermox','Descrizione di Vermox',8.10),(955,'Vesanoid','Descrizione di Vesanoid',45.84),(956,'VESIcare','Descrizione di VESIcare',48.74),(957,'Vibramycin','Descrizione di Vibramycin',84.80),(958,'Vicodin','Descrizione di Vicodin',99.15),(959,'Vicodin HP','Descrizione di Vicodin HP',60.51),(960,'Vicoprofen','Descrizione di Vicoprofen',66.41),(961,'Victoza','Descrizione di Victoza',82.15),(962,'Vimovo','Descrizione di Vimovo',17.79),(963,'Vimpat','Descrizione di Vimpat',72.10),(964,'Vinblastine Sulfate','Descrizione di Vinblastine Sulfate',59.98),(965,'Viokase','Descrizione di Viokase',31.73),(966,'Vioxx','Descrizione di Vioxx',8.88),(967,'Viread','Descrizione di Viread',40.77),(968,'VisionBlue','Descrizione di VisionBlue',82.32),(969,'Vistide','Descrizione di Vistide',0.56),(970,'Vitamin K1','Descrizione di Vitamin K1',87.89),(971,'Vivactil','Descrizione di Vivactil',62.13),(972,'Vivelle-Dot','Descrizione di Vivelle-Dot',1.08),(973,'Vusion','Descrizione di Vusion',30.41),(974,'Vytorin','Descrizione di Vytorin',58.90),(975,'Winstrol','Descrizione di Winstrol',18.23),(976,'Xigris','Descrizione di Xigris',14.63),(977,'Xolair','Descrizione di Xolair',1.28),(978,'Yellow Fever Vaccine','Descrizione di Yellow Fever Vaccine',52.84),(979,'Zaditor','Descrizione di Zaditor',48.78),(980,'Zalcitabine','Descrizione di Zalcitabine',73.00),(981,'Zanosar','Descrizione di Zanosar',51.71),(982,'Zelnorm','Descrizione di Zelnorm',19.85),(983,'Zemaira','Descrizione di Zemaira',20.74),(984,'Zemplar','Descrizione di Zemplar',11.71),(985,'Zestoretic','Descrizione di Zestoretic',14.33),(986,'Zestril','Descrizione di Zestril',29.67),(987,'Ziconotide','Descrizione di Ziconotide',62.53),(988,'Zingo','Descrizione di Zingo',43.95),(989,'Zmax','Descrizione di Zmax',22.68),(990,'Zocor','Descrizione di Zocor',9.25),(991,'Zolinza','Descrizione di Zolinza',97.76),(992,'Zolmitriptan','Descrizione di Zolmitriptan',57.29),(993,'Zonalon','Descrizione di Zonalon',87.50),(994,'Zoster Vaccine Live','Descrizione di Zoster Vaccine Live',26.98),(995,'Zosyn','Descrizione di Zosyn',34.09),(996,'Zyclara','Descrizione di Zyclara',47.93),(997,'Zyflo','Descrizione di Zyflo',47.86),(998,'Zylet','Descrizione di Zylet',1.80),(999,'Zyloprim','Descrizione di Zyloprim',2.16),(1000,'Zymaxid','Descrizione di Zymaxid',23.49);
/*!40000 ALTER TABLE `MEDICINA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEDICO`
--

DROP TABLE IF EXISTS `MEDICO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEDICO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `citta` varchar(50) NOT NULL,
  `provincia` char(2) NOT NULL,
  `codiceMedico` char(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codiceMedico` (`codiceMedico`),
  CONSTRAINT `MEDICO_ibfk_1` FOREIGN KEY (`id`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEDICO`
--

LOCK TABLES `MEDICO` WRITE;
/*!40000 ALTER TABLE `MEDICO` DISABLE KEYS */;
INSERT INTO `MEDICO` VALUES (35,'Ada','Conte','Rovereto','TN','TN2849'),(36,'Gabriele','Serra','Merano','BZ','BZ1690'),(37,'Paolo','Santoro','Merano','BZ','BZ3271'),(38,'Monica','Moretti','Trento','TN','TN7421'),(39,'Chiara','Leone','Trento','TN','TN7954');
/*!40000 ALTER TABLE `MEDICO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEDICO_SPECIALISTA`
--

DROP TABLE IF EXISTS `MEDICO_SPECIALISTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEDICO_SPECIALISTA` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `citta` varchar(50) NOT NULL,
  `provincia` char(2) NOT NULL,
  `codice` char(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codice` (`codice`),
  CONSTRAINT `MEDICO_SPECIALISTA_ibfk_1` FOREIGN KEY (`id`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEDICO_SPECIALISTA`
--

LOCK TABLES `MEDICO_SPECIALISTA` WRITE;
/*!40000 ALTER TABLE `MEDICO_SPECIALISTA` DISABLE KEYS */;
INSERT INTO `MEDICO_SPECIALISTA` VALUES (46,'Roberto','Santoro','Rovereto','TN','11803'),(47,'Tiziana','Lombardi','Trento','TN','331803'),(48,'Giacomo','Rossetti','Trento','TN','211803'),(49,'Martina','Bosco','Bolzano','BZ','431803'),(50,'Javier Paolo','Pala','Bolzano','BZ','493403');
/*!40000 ALTER TABLE `MEDICO_SPECIALISTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAZIENTE`
--

DROP TABLE IF EXISTS `PAZIENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAZIENTE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMedico` int(11) DEFAULT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `dataNascita` date NOT NULL,
  `luogoNascita` varchar(50) NOT NULL,
  `provincia` char(2) NOT NULL,
  `cf` char(16) NOT NULL,
  `sesso` char(1) NOT NULL,
  `foto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idMedico` (`idMedico`),
  CONSTRAINT `PAZIENTE_ibfk_1` FOREIGN KEY (`idMedico`) REFERENCES `MEDICO` (`id`),
  CONSTRAINT `PAZIENTE_ibfk_2` FOREIGN KEY (`id`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAZIENTE`
--

LOCK TABLES `PAZIENTE` WRITE;
/*!40000 ALTER TABLE `PAZIENTE` DISABLE KEYS */;
INSERT INTO `PAZIENTE` VALUES (22,NULL,'Andrea','Gentile','1970-11-23','Trento','TN','GNTNDR70S23L378F','M',NULL),(23,NULL,'Mario','Fontana','1994-07-16','Bolzano','BZ','FNTMRA94L16A952G','M',NULL),(24,NULL,'Alessia','Lombardi','1937-02-28','Cles','TN','LMBLSS37B68C794D','F',NULL),(25,NULL,'Nicola','Giordano','1985-05-05','Lana','BZ','GRDNCL85E05E434J','M',NULL),(26,NULL,'Aurelio','Mancini','1997-08-13','Bressanone','BZ','MNCRLA97M13B160P','M',NULL),(27,NULL,'Oscar','Pellegrini','1965-06-19','Ala','TN','PLLSCR65H19A116D','M',NULL),(28,NULL,'Ilaria','Caruso','1949-01-01','Pergine Valsugana','TN','CRSLRI49A41G452F','F',NULL),(29,NULL,'Ugo','Ferri','1918-12-26','Storo','TN','FRRGUO18T26I964B','M',NULL),(30,NULL,'Emilia','Bernardi','1981-04-10','Merano','BZ','BRNMLE81D50F132Z','F',NULL),(31,NULL,'Federica','Mazza','1954-03-15','Chiusa','BZ','MZZFRC54C55C652S','F',NULL),(32,NULL,'Mario','Farina','1927-10-16','Mori','TN','FRNMRA27R16F728T','M',NULL),(33,NULL,'Oscar','Bianco','1964-01-23','Egna','BZ','BNCSCR64A23D392H','F',NULL),(34,NULL,'Alessia','Barbieri','1989-08-20','Mezzolombardo','TN','BRBLSS89M60F187L','F',NULL),(51,NULL,'Giovanni','Rizzi','1998-02-01','Trento','TN','GNTNDR70S23L378F','M',NULL),(52,NULL,'Omar','Battan','1997-01-28','Trento','TN','BTTMRA94L16A952G','M',NULL),(53,NULL,'Marco','Menapace','1997-07-13','Trento','TN','LMBLSS37B68C794D','M',NULL);
/*!40000 ALTER TABLE `PAZIENTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRESCRIZIONE_ESAME`
--

DROP TABLE IF EXISTS `PRESCRIZIONE_ESAME`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRESCRIZIONE_ESAME` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPaziente` int(11) NOT NULL,
  `idMedico` int(11) DEFAULT NULL,
  `idEsame` int(11) NOT NULL,
  `letta` tinyint(1) DEFAULT 0,
  `data` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idPaziente` (`idPaziente`),
  KEY `idMedico` (`idMedico`),
  KEY `idEsame` (`idEsame`),
  CONSTRAINT `PRESCRIZIONE_ESAME_ibfk_1` FOREIGN KEY (`idPaziente`) REFERENCES `PAZIENTE` (`id`),
  CONSTRAINT `PRESCRIZIONE_ESAME_ibfk_2` FOREIGN KEY (`idMedico`) REFERENCES `MEDICO` (`id`),
  CONSTRAINT `PRESCRIZIONE_ESAME_ibfk_3` FOREIGN KEY (`idEsame`) REFERENCES `ESAME_PRESCRIVIBILE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRESCRIZIONE_ESAME`
--

LOCK TABLES `PRESCRIZIONE_ESAME` WRITE;
/*!40000 ALTER TABLE `PRESCRIZIONE_ESAME` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRESCRIZIONE_ESAME` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRESCRIZIONE_MEDICINA`
--

DROP TABLE IF EXISTS `PRESCRIZIONE_MEDICINA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRESCRIZIONE_MEDICINA` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPaziente` int(11) NOT NULL,
  `idMedico` int(11) NOT NULL,
  `idMedicina` int(11) NOT NULL,
  `quantita` int(11) NOT NULL,
  `data` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idPaziente` (`idPaziente`),
  KEY `idMedico` (`idMedico`),
  KEY `idMedicina` (`idMedicina`),
  CONSTRAINT `PRESCRIZIONE_MEDICINA_ibfk_1` FOREIGN KEY (`idPaziente`) REFERENCES `PAZIENTE` (`id`),
  CONSTRAINT `PRESCRIZIONE_MEDICINA_ibfk_2` FOREIGN KEY (`idMedico`) REFERENCES `MEDICO` (`id`),
  CONSTRAINT `PRESCRIZIONE_MEDICINA_ibfk_3` FOREIGN KEY (`idMedicina`) REFERENCES `MEDICINA` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRESCRIZIONE_MEDICINA`
--

LOCK TABLES `PRESCRIZIONE_MEDICINA` WRITE;
/*!40000 ALTER TABLE `PRESCRIZIONE_MEDICINA` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRESCRIZIONE_MEDICINA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUALIFICA_MEDICO_SPECIALISTA`
--

DROP TABLE IF EXISTS `QUALIFICA_MEDICO_SPECIALISTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUALIFICA_MEDICO_SPECIALISTA` (
  `idMedicoSpe` int(11) NOT NULL,
  `idEsame` int(11) NOT NULL,
  PRIMARY KEY (`idMedicoSpe`,`idEsame`),
  KEY `idEsame` (`idEsame`),
  CONSTRAINT `QUALIFICA_MEDICO_SPECIALISTA_ibfk_1` FOREIGN KEY (`idMedicoSpe`) REFERENCES `MEDICO_SPECIALISTA` (`id`),
  CONSTRAINT `QUALIFICA_MEDICO_SPECIALISTA_ibfk_2` FOREIGN KEY (`idEsame`) REFERENCES `ESAME_PRESCRIVIBILE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUALIFICA_MEDICO_SPECIALISTA`
--

LOCK TABLES `QUALIFICA_MEDICO_SPECIALISTA` WRITE;
/*!40000 ALTER TABLE `QUALIFICA_MEDICO_SPECIALISTA` DISABLE KEYS */;
/*!40000 ALTER TABLE `QUALIFICA_MEDICO_SPECIALISTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORT`
--

DROP TABLE IF EXISTS `REPORT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPORT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPrescrizione` int(11) NOT NULL,
  `descrizione` text NOT NULL,
  `data` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idPrescrizione` (`idPrescrizione`),
  CONSTRAINT `REPORT_ibfk_1` FOREIGN KEY (`idPrescrizione`) REFERENCES `PRESCRIZIONE_ESAME` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORT`
--

LOCK TABLES `REPORT` WRITE;
/*!40000 ALTER TABLE `REPORT` DISABLE KEYS */;
/*!40000 ALTER TABLE `REPORT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESET_PASSWORD_TOKENS`
--

DROP TABLE IF EXISTS `RESET_PASSWORD_TOKENS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RESET_PASSWORD_TOKENS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `tokenIndex` (`token`),
  KEY `user_id` (`userId`),
  CONSTRAINT `RESET_PASSWORD_TOKENS_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESET_PASSWORD_TOKENS`
--

LOCK TABLES `RESET_PASSWORD_TOKENS` WRITE;
/*!40000 ALTER TABLE `RESET_PASSWORD_TOKENS` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESET_PASSWORD_TOKENS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SSP`
--

DROP TABLE IF EXISTS `SSP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SSP` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `citta` varchar(255) DEFAULT NULL,
  `provincia` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `SSP_ibfk_1` FOREIGN KEY (`id`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SSP`
--

LOCK TABLES `SSP` WRITE;
/*!40000 ALTER TABLE `SSP` DISABLE KEYS */;
INSERT INTO `SSP` VALUES (44,'SS Trento','Trento','TN'),(45,'SS Bolzano','Bolzano','BZ');
/*!40000 ALTER TABLE `SSP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `type` enum('PAZIENTE','MEDICO_BASE','MEDICO_SPECIALISTA','SS_PROVINCIALE') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (22,'andrea.gentile','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','andrea.gentile@gmail.com','PAZIENTE'),(23,'mario.fontana','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','mario.fontana@gmail.com','PAZIENTE'),(24,'alessia.lombardi','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','alessia.lombardi@gmail.com','PAZIENTE'),(25,'nicola.giordano','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','nicola.giordano@gmail.com','PAZIENTE'),(26,'aurelio.mancini','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','aurelio.mancini@gmail.com','PAZIENTE'),(27,'oscar.pellegrini','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','oscar.pellegrini@gmail.com','PAZIENTE'),(28,'ilaria.caruso','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','ilaria.caruso@gmail.com','PAZIENTE'),(29,'ugo.ferri','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','ugo.ferri@gmail.com','PAZIENTE'),(30,'emilia.bernardi','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','emilia.bernardi@gmail.com','PAZIENTE'),(31,'federica.mazza','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','federica.mazza@gmail.com','PAZIENTE'),(32,'mario.farina','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','mario.farina@gmail.com','PAZIENTE'),(33,'oscar.bianco','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','oscar.bianco@gmail.com','PAZIENTE'),(34,'alessia.barbieri','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','alessia.barbieri@gmail.com','PAZIENTE'),(35,'medico.conte','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','ada.conte@gmail.com','MEDICO_BASE'),(36,'medico.serra','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','gabriele.serra@gmail.com','MEDICO_BASE'),(37,'medico.santoro-2','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','paolo.santoro@gmail.com','MEDICO_BASE'),(38,'medico.moretti','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','monica.moretti@gmail.com','MEDICO_BASE'),(39,'medico.leone','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','chiara.leone@gmail.com','MEDICO_BASE'),(44,'ssp.trento','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','info@ssp.trento.it','SS_PROVINCIALE'),(45,'ssp.bolzano','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','info@ssp.bolzano.it','SS_PROVINCIALE'),(46,'medico.santoro','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','medico.santoro@example.com','MEDICO_SPECIALISTA'),(47,'medico.lombardi','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','medico.lombardi@example.com','MEDICO_SPECIALISTA'),(48,'medico.rossetti','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','medico.rossetti@example.com','MEDICO_SPECIALISTA'),(49,'medico.bosco','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','medico.bosco@example.com','MEDICO_SPECIALISTA'),(50,'medico.pala','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','medico.pala@example.com','MEDICO_SPECIALISTA'),(51,'giovanni.rizzi','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','giovanni.rizzi@studenti.unitn.com','PAZIENTE'),(52,'omar.battan','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','omar.battan.unitn.com','PAZIENTE'),(53,'marco.menapace','$argon2i$v=19$m=65536,t=10,p=1$1uOQ9ZMimX8ypevdlWniqA$lCi1NqvmwM3ZI82njebmt4EkuNXl0pr+5TJ2yn1dKHM','marco.menapace-2@studenti.unitn.com','PAZIENTE');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-26 14:27:28
