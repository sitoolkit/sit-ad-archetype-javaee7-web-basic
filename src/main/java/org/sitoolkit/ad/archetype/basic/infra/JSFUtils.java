package org.sitoolkit.ad.archetype.basic.infra;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このクラスは、JSFのAPIの呼び出しを簡素化したメソッドを提供するユーティリティです。
 * 
 * @author SIToolkit
 *
 */
public class JSFUtils {

    private static final String MESSAGES = "facesMessages";

    /**
     * 処理中のリクエストに応じた{@code FacesContext}インスタンスを返します。
     * 
     * @return FacesContext.getCurrentInstance()
     * @see FacesContext#getCurrentInstance()
     * @throws IllegalStateException
     *             <code>FacesContext</code>が初期化されていない場合に送出します。
     */
    public static FacesContext ctx() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (ctx == null) {
            throw new IllegalStateException("FacesContextが初期化されていません。");
        }
        return ctx;
    }

    /**
     * {@code FacesContext.getExternalContext()}を返します。
     * 
     * @return FacesContext.getCurrentInstance().getExternalContext()
     * @see JSFUtils#ctx()
     */
    public static ExternalContext ext() {
        return ctx().getExternalContext();
    }

    /**
     * セッションスコープ内のオブジェクトを返します。
     * 
     * @param <T>
     *            オブジェクトの型
     * @param key
     *            オブジェクトが紐づくキー
     * @return セッションスコープ内のオブジェクト
     */
    @SuppressWarnings("unchecked")
    public static <T> T sessionScope(Object key) {
        return (T) ext().getSessionMap().get(key);
    }

    /**
     * セッションスコープ内にオブジェクトを格納します。
     * 
     * @param key
     *            オブジェクトが紐づくキー
     * @param value
     *            格納するオブジェクト
     * @return キーに紐づけられたオブジェクト
     */
    public static Object sessionScope(String key, Object value) {
        return ext().getSessionMap().put(key, value);
    }

    /**
     * 
     * @param clientId
     * @param severity
     * @param summary
     * @param detail
     */
    public static void msg(String clientId, Severity severity, String summary, String detail) {
        ctx().addMessage(clientId, new FacesMessage(severity, summary, detail));
    }

    public static void msg(String clientId, Severity severity, String message) {
        ctx().addMessage(clientId, new FacesMessage(severity, message, message));
    }

    /**
     *
     * @param severity
     * @param format
     * @param params
     */
    public static void msg(Severity severity, String format, Object... params) {
        msg(null, severity, MessageFormat.format(format, params));
    }

    public static void info(String format, Object... params) {
        msg(FacesMessage.SEVERITY_INFO, format, params);
    }

    public static void error(String format, Object... params) {
        msg(FacesMessage.SEVERITY_ERROR, format, params);
    }

    /**
     * 処理中のリクエストに応じた{@code HttpServletResponse}インスタンスを返します。
     * 
     * @return
     */
    public static HttpServletResponse res() {
        return (HttpServletResponse) ext().getResponse();
    }

    /**
     * 処理中のリクエストに応じた{@code HttpServletRequest}インスタンスを返します。
     * 
     * @return
     */
    public static HttpServletRequest req() {
        return (HttpServletRequest) ext().getRequest();
    }

    /**
     * リダイレクトとして処理されるように加工したViewIdを返します。
     * 
     * @param viewId
     *            リダイレクト先のViewId
     * @return リダイレクトとして処理されるViewId
     */
    public static String redirect(String viewId) {
        backupMessage();
        return viewId + "?faces-redirect=true";
    }

    /**
     * 指定のURLへリダイレクトします。
     * 
     * @param url
     *            リダイレクト先のURL
     * @return 空文字
     * @see ExternalContext#redirect(java.lang.String)
     */
    public static String redirectUrl(String url) {
        backupMessage();
        try {
            ext().redirect(url);
        } catch (IOException e) {
            throw new FrameworkException(e);
        }
        return "";
    }

    /**
     * コンテキストルートへリダイレクトします。
     * 
     * @return 空文字
     * @see #redirectUrl(java.lang.String)
     */
    public static String redirectHome() {
        return redirectUrl(ext().getRequestContextPath() + "/");
    }

    private static void backupMessage() {
        if (!ctx().getMessageList().isEmpty()) {
            sessionScope(MESSAGES, JSFUtils.ctx().getMessageList());
        }
    }

    /**
     * セッションスコープに退避している{@code FacesMessage}リストを {@code FacesContext}に追加します。
     * 
     * @see FacesContext#addMessage(java.lang.String,
     *      javax.faces.application.FacesMessage)
     */
    public static void restoreMessage() {
        List<FacesMessage> msgs = sessionScope(MESSAGES);
        if (msgs != null && !msgs.isEmpty()) {
            for (FacesMessage msg : msgs) {
                ctx().addMessage("", msg);
            }
            sessionScope(MESSAGES, null);
        }
    }

    /**
     * ViewRootのViewIdを返します。
     * 
     * @return ViewRootのViewId
     */
    public static String viewId() {
        return ctx().getViewRoot().getViewId();
    }

    /**
     * 呼び出し元のリクエスト内でforwardが行われていた場合、元のサーブレットのパスを返します。
     * forwardが行われていなかった場合、nullを返します。
     * 
     * @return 元のサーブレットのパス
     */
    public static String forwardSrcPath() {
        Object obj = JSFUtils.req().getAttribute("javax.servlet.forward.servlet_path");
        return obj == null ? null : obj.toString();
    }
}
