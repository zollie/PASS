
calcNetProfit <- function(facts, preds, cutoff) {
  vals <- sapply(preds, function(y) { ifelse(y<cutoff,0, 1) })  
  ct <- CrossTable(facts, vals, dnn = c("Actual", "Predicted"))
  print("Profit with cutoff")
  print(cutoff)
  profitFromCrossTable(ct)
}

profitFromCrossTable <- function(ct) {
  profit <- ct$t[1,1] * 100
  loss <- ct$t[2,1] * -500
  profit - loss
}
