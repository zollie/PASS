import cls
import tm

trnpfile = 'tmsk.trn.properties'
tstpfile = 'tmsk.tst.properties'
cat = 'earn'

train_data, train_vocab = cls.create_training_data(cat, 50, trnpfile)
train_f = train_data[0] + train_data[1]
train_l = [0] * len(train_data[0]) + [1] * len(train_data[1])

test_data = cls.create_testing_data(cat, train_vocab, tstpfile)
test_f = test_data[0] + test_data[1]
test_l = [0] * len(test_data[0]) + [1] * len(test_data[1])


def q1():
    print 'begin q1()'
    clf = cls.train_decision_tree(train_f, train_l)

    # NOTE: I edeited the cls.py to get this to work
    cls.print_decision_tree(clf, 'clf.out')

    pred = cls.evaluate_classifier(clf, test_f)

    cls.compute_evaluation_metrics(test_l, pred)
    print 'end q1()'


def q2():
    print 'begin q2()'
    clf = cls.train_decision_tree2(train_f, train_l)
    # NOTE: I edeited the cls.py to get this to work
    cls.print_decision_tree(clf, 'clf.out')
    pred = cls.evaluate_classifier(clf, test_f)
    cls.compute_evaluation_metrics(test_l, pred)

    clf = cls.train_decision_tree3(train_f, train_l)
    # NOTE: I edeited the cls.py to get this to work
    cls.print_decision_tree(clf, 'clf.out')
    pred = cls.evaluate_classifier(clf, test_f)
    cls.compute_evaluation_metrics(test_l, pred)

    clf = cls.train_decision_tree4(train_f, train_l)
    # NOTE: I edeited the cls.py to get this to work
    cls.print_decision_tree(clf, 'clf.out')
    pred = cls.evaluate_classifier(clf, test_f)
    cls.compute_evaluation_metrics(test_l, pred)
    print 'end q2()'


def q4():
    print 'begin q4()'
    clf = cls.train_naive_bayes(train_f, train_l)
    pred = cls.evaluate_classifier(clf, test_f)
    cls.compute_evaluation_metrics(test_l, pred)
    print 'end q4()'


def q5():
    print 'begin q5()'
    clf = cls.train_linear_model(train_f, train_l)
    pred = cls.evaluate_classifier(clf, test_f)
    cls.compute_evaluation_metrics(test_l, pred)
    print 'end q5()'


def q6():
    print 'begin q6()'
    train_f_tfidf = cls.extract_tfidf_feature(train_f)
    test_f_tfidf = cls.extract_tfidf_feature(test_f)

    clf = cls.train_linear_model(train_f_tfidf, train_l)
    pred = cls.evaluate_classifier(clf, test_f_tfidf)
    cls.compute_evaluation_metrics(test_l, pred)
    print 'end q6()'


def q8():
    print 'begin q8()'
    train_f_k = cls.extract_frequent_k_bow_feature(train_f, 50)
    test_f_k = cls.extract_frequent_k_bow_feature(test_f, 50)

    clf = cls.train_decision_tree(train_f_k, train_l)
    pred = cls.evaluate_classifier(clf, test_f_k)
    cls.compute_evaluation_metrics(test_l, pred)

    clf = cls.train_linear_model(train_f_k, train_l)
    pred = cls.evaluate_classifier(clf, test_f_k)
    cls.compute_evaluation_metrics(test_l, pred)
    print 'end q8()'


if __name__ == '__main__':
    # q1()
    # q2()
    # q4()
    # q5()
    # q6()
    q8()

