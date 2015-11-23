package org.sitoolkit.ad.archetype.basic.infra.layer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;

/**
 * このアノテーションは、DAOクラスが宣言すべきステレオタイプです。
 * @author SIToolkit
 */
@Stereotype
@Named
@ApplicationScoped
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Dao {

}
