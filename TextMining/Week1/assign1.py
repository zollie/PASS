import pickle
import tm

tm_train = tm.TextMiner('tmsk.properties')


def one_a():
    global tm_train
    tm_train.tokenize()
    tm_train.mkdict(500)
    print 'vocab has length of ' + str(len(tm_train.vocab))
    print ','.join(tm_train.vocab)


def one_b():
    global tm_train
    tm_train.stopwords()
    tm_train.stem()
    tm_train.mkdict(500)
    print 'vocab has length of ' + str(len(tm_train.vocab))
    print ','.join(tm_train.vocab)


def one_c():
    global tm_train
    # the next two props are not used but I think work in the Java TMSK
    # I think clean_text() has some of this hardcoded
    tm_train.properties['word-delimiters'] = '\' , . ; : ! ? {} <> + " \n \t'
    tm_train.properties['whitespace-chars'] = ' _ \t \n \r'
    tm_train.tokenize()
    tm_train.stopwords()
    tm_train.stem()
    tm_train.mkdict('earn', 50)
    print 'vocab has length of ' + str(len(tm_train.vocab))
    print ','.join(tm_train.vocab)


def two():
    global tm_train
    tm_train.vectorize()
    pickle.dump(tm_train, open("train.p", "wb"))


if __name__ == '__main__':
    one_a()
    one_b()
    one_c()
    two()

