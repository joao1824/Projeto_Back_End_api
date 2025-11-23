package com.projeto.api.specification;

import com.projeto.api.models.Artista;
import com.projeto.api.models.Musica;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class MusicaSpecification {

    public static Specification<Musica> aplicarFiltros(Map<String, String> filtros) {
        return (root, query, builder) -> {

            //Vou ver se coloco exception, acho que sim

            query.distinct(true); // Evita resultados duplicados em joins
            Predicate predicate = builder.conjunction();

            for (Map.Entry<String, String> entry : filtros.entrySet()) {
                String campo = entry.getKey();   // nome do campo a ser filtrado
                String valor = entry.getValue();
                if (valor == null || valor.isEmpty()) continue;

                Path<?> path = root;
                // Lidar com campos relacionados (joins)
                if (campo.contains(".")) {
                    String[] partes = campo.split("\\.");// Dividir o campo em partes para joins
                    for (int i = 0; i < partes.length - 1; i++) {
                        path = ((From<?, ?>) path).join(partes[i]);
                    }


                    path = path.get(partes[partes.length - 1]);
                } else {
                    path = root.get(campo);
                }

                // Verificar o tipo do campo para aplicar o filtro adequado
                if (valor.matches("\\d+(\\.\\d+)?")) {// Se o valor for numérico
                    predicate = builder.and(predicate, builder.equal(path, Double.parseDouble(valor)));
                } else {//
                    predicate = builder.and(predicate, builder.like(builder.lower(path.as(String.class)), "%" + valor.toLowerCase() + "%"));
                }
            }

            return predicate;
        };
    }
}



