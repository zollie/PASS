library(class)
library(randomForest)
library(DMwR)
# bioconductor
library(Biobase)
library(genefilter)

load('myALL.Rdata')

data <- exprs(ALLb)
rows.train <- sample(nrow(data), 2)
data.train <- data[rows.train,]

dt <- data.frame(t(data.train),Mut=ALLb$mol.bio)
DSs <- list(dataset(Mut ~ .,dt,'ALL'))

# The learners to evaluate
TODO <- c('knn','randomForest')
for(td in TODO) {
  assign(td,
         experimentalComparison(
           DSs,
           c(
             do.call('variants',
                     c(list('genericModel',learner=td),
                       vars[[td]],
                       varsRootName=td))
           ),
           loocvSettings(seed=1234,verbose=F),
           'itsInfo'=T
         )
  )
  save(list=td,file=paste(td,'Rdata',sep='.'))
}
