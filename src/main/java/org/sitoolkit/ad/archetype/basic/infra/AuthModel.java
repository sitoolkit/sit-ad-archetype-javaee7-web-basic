package org.sitoolkit.ad.archetype.basic.infra;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * このクラスは、認証処理のファサードとなる{@code ManagedBean}です。
 *
 * @author SIToolkit
 *
 */
@Model
@RequestScoped
public class AuthModel {

    private static final Logger LOG = LoggerFactory.getLogger(AuthModel.class);
    private static final String ANONYMOUS_USER = "anonymous";

    private String loginId;

    private String password;

    private String requestedViewId;

    @Inject
    private HttpServletRequest request;

    /**
     * 内部保持するログインID、パスワードを用いて認証を行います。 認証処理は{@link HttpServletRequest.login}
     * メソッドに委譲します。
     *
     * @return 認証が成功し、かつ直接ログイン画面に遷移して認証を行っている場合は、ログイン画面のViewIdを返します。
     *         認証が成功し、かつログイン画面とは別の画面への遷移でログイン画面にフォワードされた場合は、
     *         元々遷移しようとしていた画面のViewIdを返します。 認証が失敗した場合はログイン画面のViewIdを返します。
     * @see HttpServletRequest#login
     */
    public String login() {
        try {
            JSFUtils.req().login(getLoginId(), getPassword());
            JSFUtils.info("ログインしました。");

            if (StringUtils.isEmpty(getRequestedViewId())) {
                return JSFUtils.viewId();
            } else {
                return JSFUtils.redirect(getRequestedViewId());
            }
        } catch (ServletException e) {
            JSFUtils.error("ログインIDまたはパスワードが不正です。");
            return JSFUtils.viewId();
        }
    }

    /**
     * 認証済ユーザーのログアウト処理を行います。 処理は{@link HttpServletRequest.logout}メソッドに委譲します。
     * ログアウト処理後は、コンテキストルート直下にリダイレクトします。
     *
     * @return 空文字
     * @see JSFUtils#redirectHome()
     */
    public String logout() {
        try {
            request.logout();
            JSFUtils.info("ログアウトしました。");
        } catch (ServletException e) {
            LOG.error("ログアウト処理で例外が発生しました。", e);
        }

        return JSFUtils.redirectHome();
    }

    /**
     * ログインIDを取得します。
     *
     * @return ログインID
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * ログインIDを設定します。
     *
     * @param loginId
     *            設定するログインID
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * パスワードを取得します。
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定します。
     *
     * @param password
     *            設定するパスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 元々リクエストされたフォワード前のViewIdを返します。
     * 
     * @return 元々リクエストされたフォワード前のViewId
     */
    public String getRequestedViewId() {
        if (requestedViewId == null) {
            requestedViewId = JSFUtils.forwardSrcPath();
        }
        return requestedViewId;
    }

    /**
     * 元々リクエストされたフォワード前のViewIdを設定します。
     * 
     * @param requestedViewId
     *            元々リクエストされたフォワード前のViewId
     */
    public void setRequestedViewId(String requestedViewId) {
        this.requestedViewId = requestedViewId;
    }

}
