package fr.fs.repositories;

import fr.fs.entities.Article;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleRepository implements PanacheRepositoryBase <Article, Integer> {
}
