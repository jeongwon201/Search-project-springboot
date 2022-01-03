import json
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

DATA_IN_PATH = 'C:\\Users\\jeong\\Desktop\\Eclipse\\workspace-spring-jpa-thymeleaf\\' \
               'Search\\src\\main\\resources\\static\\csv\\'

data = pd.read_csv(DATA_IN_PATH + 'data.csv', header=0, delimiter='\t', quoting=3)
data['keyword'] = data['keyword'].fillna('')

tfidf = TfidfVectorizer()
tfidf_matrix = tfidf.fit_transform(data['keyword'])

cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)

indices = pd.Series(data.index, index=data['keyword']).drop_duplicates()


def get_recommendations(keyword, cosine_sim=cosine_sim):
    idx = indices[keyword]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:11]
    item_indices = [i[0] for i in sim_scores]

    for key in sim_scores:
        if key[1] == 0.0:
            item_indices.remove(key[0])

    item_keywords = []
    for i in item_indices:
        item_keywords.append(indices.index[i])

    res = ", ".join(item_keywords)

    return res


py_dic_list = []

for i in data['keyword']:
    py_dic = {}
    py_dic["keyword"] = i
    py_dic["keywordList"] = get_recommendations(i)
    py_dic_list.append(py_dic)

json_data = json.dumps(py_dic_list, ensure_ascii=False, indent="\t")

# def get_recommendations(keyword, cosine_sim=cosine_sim):
#     idx = indices[keyword]
#     sim_scores = list(enumerate(cosine_sim[idx]))
#     sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
#     sim_scores = sim_scores[1:11]
#     item_indices = [i[0] for i in sim_scores]
#
#     item_keywords = []
#     for i in item_indices:
#         item_keywords.append(indices.index[i])
#
#     res = json.dumps(item_keywords, ensure_ascii=False, indent="\t")
#
#     return res
#
#
# py_dic = {}
# for i in data['keyword']:
#     py_dic[i] = get_recommendations(i)
#
# json_data = json.dumps(py_dic, ensure_ascii=False, indent="\t")
