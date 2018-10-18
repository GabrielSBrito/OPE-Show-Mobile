from flask import Flask, jsonify, request
import json
import urllib.request
import random

app = Flask(__name__)

#Usuarios = [{"id" : "0", "usuario" : "aluno", "senha" : "impacta"} ]

kits = [ {"id" : e, "nome" : "Kit "+str(e), "lote" : "Lote "+str(e), "foto" : "https://http2.mlstatic.com/chocolate-cacau-show-gourmet-delicias-500g-D_NQ_NP_870809-MLB26429034745_112017-F.jpg", "itens" : "Itens "+str(e)} for e in range(1,3)]

@app.route("/login", methods=['POST'])
def getLoginUsuarioSenha():
	retorno = False
	content = request.get_json()
	if content["nome"] == "aluno" and content["senha"] == "impacta":
	    retorno = True
	return jsonify(retorno)

@app.route("/kits", methods=['GET'])
def get():
    return jsonify(kits)

@app.route("/kits/<int:id>", methods=['GET'])
def get_one(id):
    filtro = [e for e in kits if e["id"] == id]
    if filtro:
        return jsonify(filtro[0])
    else:
        return jsonify({})

@app.route("/kits", methods=['POST'])
def post():
    global kits
    try:
        content = request.get_json()

        # gerar id
        ids = [e["id"] for e in kits]
        if ids:
            nid = max(ids) + 1
        else:
            nid = 1
        content["id"] = nid
        kits.append(content)
        return jsonify({"status":"OK", "msg":"kits adicionado com sucesso"})
    except Exception as ex:
        return jsonify({"status":"ERRO", "msg":str(ex)})

@app.route("/kits/<int:id>", methods=['DELETE'])
def delete(id):
    global kits
    try:
        kits = [e for e in kits if e["id"] != id]
        return jsonify({"status":"OK", "msg":"kits removido com sucesso"})
    except Exception as ex:
        return jsonify({"status":"ERRO", "msg":str(ex)})

@app.route("/push/<string:key>/<string:token>", methods=['GET'])
def push(key, token):
	d = random.choice(kits)
	data = {
		"to": token,
		"notification" : {
			"title":d["nome"],
			"body":"Você tem nova atividade em "+d['nome']
		},
		"data" : {
			"kitId":d['id']
		}
	}
	req = urllib.request.Request('http://fcm.googleapis.com/fcm/send')
	req.add_header('Content-Type', 'application/json')
	req.add_header('Authorization', 'key='+key)
	jsondata = json.dumps(data)
	jsondataasbytes = jsondata.encode('utf-8')   # needs to be bytes
	req.add_header('Content-Length', len(jsondataasbytes))
	response = urllib.request.urlopen(req, jsondataasbytes)
	print(response)
	return jsonify({"status":"OK", "msg":"Push enviado"})


if __name__ == "__main__":
    app.run(host='0.0.0.0')

