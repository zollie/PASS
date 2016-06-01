## @knitr dataR
#
# Load, clean, and setup data
#

# RNG seed for reproducibility
set.seed(12345)

getRandomRowNums <- function(dd=getDataRaw(), percent=.6) {
    n <- nrow(dd)
    a <- sort(sample(1:n, floor(n*percent)))
    a
}

getDataRaw <- function() {
  dd <- read.csv("~/R/PASS/PredictiveModeling/DirectMailPrediction/DonorData.csv")
}

getDataClean <- function(dd=getDataRaw()) {
  dd$Row.Id <- NULL
  dd$Row.Id. <- NULL
  dd$row.names <- NULL
  dd$TARGET_D <- NULL
  
  dd
}

getDataWithLevels <- function(dd=getDataClean()) {
  dd$homeowner.dummy <- factor(dd$homeowner.dummy)
  dd$gender.dummy <- factor(dd$gender.dummy)
  dd$INCOME <- factor(dd$INCOME)
  dd$WEALTH <- factor(dd$WEALTH)
  
  dd$TARGET_B <- factor(dd$TARGET_B)
  
  dd
}

getNnData <- function(dd=getDataClean()) {
  dd$WEALTH <- nnNormCol(dd$WEALTH)
  dd$HV <- nnNormCol(dd$HV)
  dd$lcmed <- nnNormCol(dd$Icmed)
  dd$lcavg <- nnNormCol(dd$Icavg)
  dd$IC15 <- nnNormCol(dd$IC15)
  dd$NUMPROM <- nnNormCol(dd$NUMPROM)
  dd$RAMNTALL <- nnNormCol(dd$RAMNTALL)
  dd$MAXRAMNT <- nnNormCol(dd$MAXRAMNT)
  dd$LASTGIFT <- nnNormCol(dd$LASTGIFT)
  dd$totalmonths <- nnNormCol(dd$totalmonths)
  dd$TIMELAG <- nnNormCol(dd$TIMELAG)
  dd$AVGGIFT <- nnNormCol(dd$AVGGIFT)
  dd
}

getNnDataPruned <- function(dd=defaultReducePredictors(getDataClean())) {
  #dd$WEALTH <- nnNormCol(dd$WEALTH)
  #dd$HV <- nnNormCol(dd$HV)
  #dd$lcmed <- nnNormCol(dd$Icmed)
  #dd$lcavg <- nnNormCol(dd$Icavg)
  #dd$IC15 <- nnNormCol(dd$IC15)
  dd$NUMPROM <- nnNormCol(dd$NUMPROM)
  dd$RAMNTALL <- nnNormCol(dd$RAMNTALL)
  dd$MAXRAMNT <- nnNormCol(dd$MAXRAMNT)
  dd$LASTGIFT <- nnNormCol(dd$LASTGIFT)
  dd$totalmonths <- nnNormCol(dd$totalmonths)
  dd$TIMELAG <- nnNormCol(dd$TIMELAG)
  #dd$AVGGIFT <- nnNormCol(dd$AVGGIFT)
  dd
}


nnNormCol <- function(col) {
  a <- min(col, na.rm=TRUE) 
  b <- max(col, na.rm=TRUE)
  
  c2 <- sapply(col, function(x) {
    (x-a)/(b-a)
  })
  c2
}


getFutureDataRaw <- function() {
  dd <- 
    read.csv("~/R/PASS/PredictiveModeling/DirectMailPrediction/FutureDonorData.csv")
}

getFutureDataClean <- function(dd=getFutureDataRaw()) {
  dd$Row.Id <- NULL
  dd$Row.Id. <- NULL

  dd$X <- NULL
  dd$X.1 <- NULL
  dd$X.2 <- NULL
  dd$X.3 <- NULL
  dd$X.4 <- NULL
  dd$X.5 <- NULL
  dd$X.6 <- NULL
  
  dd
}

getFutureDataWithLevels <- function(dd=getFutureDataClean()) {
  dd$homeowner.dummy <- factor(dd$homeowner.dummy)
  dd$gender.dummy <- factor(dd$gender.dummy)
  dd$INCOME <- factor(dd$INCOME)
  dd$WEALTH <- factor(dd$WEALTH)
  dd
  dd
}

reducePredictors <- function(dd=getDataWithLevels(), drops) {
  dd[,!(names(dd) %in% drops)]
}

defaultReducePredictors <- function(dd=getDataWithLevels()) {
  # In the interests of parsimony we reduce some independents
  drops <- c("zipconvert_2", "zipconvert_3", "zipconvert_4", "zipconvert_5", "WEALTH", "HV", "Icmed", "Icavg", "IC15", "AVGGIFT")
  reducePredictors(dd, drops)
}

# price matrix 
prices <- matrix(c(0,0,-.68,13-.68), 2, 2)

