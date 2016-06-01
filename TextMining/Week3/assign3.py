import cls
import tm
import ret

trnpfile = 'tmsk.trn.properties'
tstpfile = 'tmsk.tst.properties'
queryfile = 'tmsk.query.properties'

def q2():
    print 'begin q2()'
    trnbow, trnvocab = ret.create_document_vectors(trnpfile, 10)
    querybow = ret.create_query_vectors(trnvocab, queryfile)
    docids = ret.find_similar_k_docs(querybow, trnbow, 10)
    print 'k=10 docsids ...'
    print docids
    print 'end q2()'


def q3():
    print 'begin q3()'
    trnbow, trnvocab = ret.create_document_vectors(trnpfile, 10)
    print 'k-means clustering with k=10 ...'
    km, scores = ret.cluster_k_means(trnbow, 10)
    print 'silhouette coefficient for k=10 is: '+str(scores)
    print 'k-means clustering with k=20 ...'
    km, scores = ret.cluster_k_means(trnbow, 20)
    print 'silhouette coefficient for k=20 is: '+str(scores)
    print 'k-means clustering with k=30 ...'
    km, scores = ret.cluster_k_means(trnbow, 30)
    print 'silhouette coefficient for k=30 is: '+str(scores)
    print 'end q3()'


if __name__ == '__main__':
    q2()
    q3()

