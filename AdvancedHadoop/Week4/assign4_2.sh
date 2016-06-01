#!/bin/bash

rm -rf ~/spam/labelindex
rm -rf ~/spam/*seq
rm -rf ~/spam/*vectors
rm -rf ~/*seq
rm -rf ~/*vectors
rm -rf ~/labelindex
rm -rf ~/model


$MAHOUT_HOME/bin/mahout seqdirectory -i ~/spam -o ~/spam-seq -ow

$MAHOUT_HOME/bin/mahout seq2sparse -i ~/spam-seq -o ~/spam-vectors -lnorm -nv -wt tfidf

#$MAHOUT_HOME/bin/mahout seqdirectory -i ~/spam/easy_ham -o ~/spam/easy_ham-seq -ow
#$MAHOUT_HOME/bin/mahout seq2sparse -i ~/spam/easy_ham -o ~/spam/easy_ham-vectors -lnorm -nv -wt tfidf

$MAHOUT_HOME/bin/mahout split -i ~/spam-vectors --trainingOutput ~/spam-train-vectors --testOutput \
~/spam-test-vectors --randomSelectionPct 40 --overwrite --sequenceFiles -xm sequential

$MAHOUT_HOME/bin/mahout trainnb -i ~/spam-train-vectors -el -o ~/model -li ~/labelindex -ow -c

$MAHOUT_HOME/bin/mahout testnb -i ~/spam-test-vectors -m ~/model -l ~/labelindex -o ~/spam-testing -ow –c