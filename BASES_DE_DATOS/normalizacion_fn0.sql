WITH urls_numeradas AS (
    SELECT 
        u.userId,
        u.nombre,
        e.empresa,
        e.direccion_empresa,
        urls.url,
        ROW_NUMBER() OVER (
            PARTITION BY u.userId 
            ORDER BY urls.urlId
        ) AS rn
    FROM empresas e
    JOIN usuarios u ON u.relEmpresaId = e.emprId
    JOIN url_relations ur ON u.userId = ur.relatedUserId
    JOIN urls ON ur.relatedUrlId = urls.urlId
)
SELECT
    nombre,
    empresa,
    direccion_empresa,
    MAX(CASE WHEN rn = 1 THEN url END) AS url1,
    MAX(CASE WHEN rn = 2 THEN url END) AS url2
FROM urls_numeradas
GROUP BY nombre, empresa, direccion_empresa;
