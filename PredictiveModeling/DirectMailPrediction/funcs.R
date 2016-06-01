#
# Common, reusable functions
#

buildClassTab <- function(p, p.target, cutoff=.5) {
  require(gmodels)
  if(is.null(cutoff)) {
    p.vals = p;
  } else {
    p.vals <- sapply(p, function(y) { ifelse(y < cutoff, 0, 1) })  
  }
  CrossTable(p.target, p.vals, type="SPSS", dnn=c("Actual", "Predicted"))
}

drawRoc <- function(p, p.target) {
  require(ROCR)
  p.rocr <- prediction(p, p.target)
  p.rocr.roc <- performance(p.rocr, "tpr", "fpr")
  plot(p.rocr.roc, main="ROC Curve", colorize=T)
} 

drawLift <- function(p, p.target, add=FALSE) {
  require(ROCR)
  p.rocr <- prediction(p, p.target)
  p.rocr.lift <- performance(p.rocr, "lift", "rpp")
  plot(p.rocr.lift, add=add, main="Lift Curve", colorize=T)
} 

adjustTabForOversamp <- function(ct, target, dnn=c("Actual", "Predicted")) {
  t <- ct$t
  actual.0 <- t[1,1]+t[1,2]
  actual.1 <- t[2,1]+t[2,2]
  prop.0 <- 1-target
  
  n <- target*100
  x <- actual.1*100 / n
  
  new.0 <- x*prop.0
  ct
  x.0 <- ct$prop.row[1,1]*new.0
  x.1 <- ct$prop.row[1,2]*new.0
  
  row1 <- matrix(c(x.0, x.1), 1, 2)
  row2 <- matrix(c(t[2,1], t[2,2]), 1, 2)
  df <- rbind(row1, row2)
  CrossTable(df, dnn=dnn)   
}

netFromCrossTab <- function(ct, prices) {
  t <- ct$t
  x00 <- t[1,1]*prices[1,1]
  x01 <- t[1,2]*prices[1,2]
  x10 <- t[2,1]*prices[2,1]
  x11 <- t[2,2]*prices[2,2]
  sum(x00,x01,x10,x11)
}


buildClassTree <- function(formula, data, minspl, minbuc) {
  require(rpart)
  tree.g <- rpart(formula, data=data, method="class", minsplit=minspl, minbucket=minbuc)
  # pruned with min xerror
  tree.p <- prune(tree.g, tree.g$cptable[which.min(tree.g$cptable[,"xerror"]),"CP"])
  tree.p
}
