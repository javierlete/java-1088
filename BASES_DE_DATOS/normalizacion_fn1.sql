SELECT u.nombre, e.empresa, e.direccion_empresa, urls.url
FROM empresas e
JOIN usuarios u ON u.relEmpresaId = e.emprId
JOIN url_relations ur ON u.userId = ur.relatedUserId
JOIN urls ON ur.relatedUrlId = urls.urlId