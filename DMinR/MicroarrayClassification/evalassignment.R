require(class,quietly=TRUE)
require(randomForest,quietly=TRUE)

load('knn.Rdata')
load('randomForest.Rdata')

all.trials <- join(knn,randomForest,by='variants')

#summary(all.trials)

rankSystems(all.trials,top=10,max=T)

bestScores(all.trials)

#plot(all.trials)

best <- getVariant('randomForest.v3', all.trials)

attr(knn, "itInfo")[1:4]