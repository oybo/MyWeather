package app.android.oyb.com.myapp.bean;

/**
 * Created by O on 2017/2/24.
 */

public class User {

    /**
     * id : 20
     * name : oyb1234
     * avatar : /public/image/noavatar.gif
     * reg_ip : 119.123.113.127
     * reg_time : 1486630769
     * login_ip : 121.34.131.43
     * login_time : 1487918919
     * qqid :
     * weiboid :
     * weixinid :
     * status : 1
     * point : 27
     * exp : 75
     * author : 0
     * fromid : 0
     * device_type : 0
     * device_id :
     * data : {"user_id":"20","login_num":"4","vote_num":"3","star_num":"0","comment_num":"0","invite_reg_num":"0",
     * "invite_visit_num":"0","today_vote":"0","today_comment":"0","today_star":"0","today_invite_reg":"0","today_invite_visit":"0",
     * "sign_day":"0","login_day":"20170224","op_day":"20170224","comment_tip":"0"}
     * group : {"id":"1","name":"初入江湖","intro":"初入江湖","exp":"0","limitmark":"5","limitstar":"5","limitvote":"5","limitcomment":"5",
     * "max_sign":"5","max_star":"5","max_vote":"5","max_comment":"5","max_invite_visit":"5","max_invite_reg":"5"}
     */

    private String id;
    private String name;
    private String avatar;
    private String reg_ip;
    private String reg_time;
    private String login_ip;
    private String login_time;
    private String qqid;
    private String weiboid;
    private String weixinid;
    private String status;
    private String point;
    private String exp;
    private String author;
    private String fromid;
    private String device_type;
    private String device_id;
    /**
     * user_id : 20
     * login_num : 4
     * vote_num : 3
     * star_num : 0
     * comment_num : 0
     * invite_reg_num : 0
     * invite_visit_num : 0
     * today_vote : 0
     * today_comment : 0
     * today_star : 0
     * today_invite_reg : 0
     * today_invite_visit : 0
     * sign_day : 0
     * login_day : 20170224
     * op_day : 20170224
     * comment_tip : 0
     */

    private DataNameBean data;
    /**
     * id : 1
     * name : 初入江湖
     * intro : 初入江湖
     * exp : 0
     * limitmark : 5
     * limitstar : 5
     * limitvote : 5
     * limitcomment : 5
     * max_sign : 5
     * max_star : 5
     * max_vote : 5
     * max_comment : 5
     * max_invite_visit : 5
     * max_invite_reg : 5
     */

    private GroupNameBean group;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getReg_ip() {
        return reg_ip;
    }

    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getQqid() {
        return qqid;
    }

    public void setQqid(String qqid) {
        this.qqid = qqid;
    }

    public String getWeiboid() {
        return weiboid;
    }

    public void setWeiboid(String weiboid) {
        this.weiboid = weiboid;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public DataNameBean getData() {
        return data;
    }

    public void setData(DataNameBean data) {
        this.data = data;
    }

    public GroupNameBean getGroup() {
        return group;
    }

    public void setGroup(GroupNameBean group) {
        this.group = group;
    }

    public static class DataNameBean {
        private String user_id;
        private String login_num;
        private String vote_num;
        private String star_num;
        private String comment_num;
        private String invite_reg_num;
        private String invite_visit_num;
        private String today_vote;
        private String today_comment;
        private String today_star;
        private String today_invite_reg;
        private String today_invite_visit;
        private String sign_day;
        private String login_day;
        private String op_day;
        private String comment_tip;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLogin_num() {
            return login_num;
        }

        public void setLogin_num(String login_num) {
            this.login_num = login_num;
        }

        public String getVote_num() {
            return vote_num;
        }

        public void setVote_num(String vote_num) {
            this.vote_num = vote_num;
        }

        public String getStar_num() {
            return star_num;
        }

        public void setStar_num(String star_num) {
            this.star_num = star_num;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getInvite_reg_num() {
            return invite_reg_num;
        }

        public void setInvite_reg_num(String invite_reg_num) {
            this.invite_reg_num = invite_reg_num;
        }

        public String getInvite_visit_num() {
            return invite_visit_num;
        }

        public void setInvite_visit_num(String invite_visit_num) {
            this.invite_visit_num = invite_visit_num;
        }

        public String getToday_vote() {
            return today_vote;
        }

        public void setToday_vote(String today_vote) {
            this.today_vote = today_vote;
        }

        public String getToday_comment() {
            return today_comment;
        }

        public void setToday_comment(String today_comment) {
            this.today_comment = today_comment;
        }

        public String getToday_star() {
            return today_star;
        }

        public void setToday_star(String today_star) {
            this.today_star = today_star;
        }

        public String getToday_invite_reg() {
            return today_invite_reg;
        }

        public void setToday_invite_reg(String today_invite_reg) {
            this.today_invite_reg = today_invite_reg;
        }

        public String getToday_invite_visit() {
            return today_invite_visit;
        }

        public void setToday_invite_visit(String today_invite_visit) {
            this.today_invite_visit = today_invite_visit;
        }

        public String getSign_day() {
            return sign_day;
        }

        public void setSign_day(String sign_day) {
            this.sign_day = sign_day;
        }

        public String getLogin_day() {
            return login_day;
        }

        public void setLogin_day(String login_day) {
            this.login_day = login_day;
        }

        public String getOp_day() {
            return op_day;
        }

        public void setOp_day(String op_day) {
            this.op_day = op_day;
        }

        public String getComment_tip() {
            return comment_tip;
        }

        public void setComment_tip(String comment_tip) {
            this.comment_tip = comment_tip;
        }
    }

    public static class GroupNameBean {
        private String id;
        private String name;
        private String intro;
        private String exp;
        private String limitmark;
        private String limitstar;
        private String limitvote;
        private String limitcomment;
        private String max_sign;
        private String max_star;
        private String max_vote;
        private String max_comment;
        private String max_invite_visit;
        private String max_invite_reg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public String getLimitmark() {
            return limitmark;
        }

        public void setLimitmark(String limitmark) {
            this.limitmark = limitmark;
        }

        public String getLimitstar() {
            return limitstar;
        }

        public void setLimitstar(String limitstar) {
            this.limitstar = limitstar;
        }

        public String getLimitvote() {
            return limitvote;
        }

        public void setLimitvote(String limitvote) {
            this.limitvote = limitvote;
        }

        public String getLimitcomment() {
            return limitcomment;
        }

        public void setLimitcomment(String limitcomment) {
            this.limitcomment = limitcomment;
        }

        public String getMax_sign() {
            return max_sign;
        }

        public void setMax_sign(String max_sign) {
            this.max_sign = max_sign;
        }

        public String getMax_star() {
            return max_star;
        }

        public void setMax_star(String max_star) {
            this.max_star = max_star;
        }

        public String getMax_vote() {
            return max_vote;
        }

        public void setMax_vote(String max_vote) {
            this.max_vote = max_vote;
        }

        public String getMax_comment() {
            return max_comment;
        }

        public void setMax_comment(String max_comment) {
            this.max_comment = max_comment;
        }

        public String getMax_invite_visit() {
            return max_invite_visit;
        }

        public void setMax_invite_visit(String max_invite_visit) {
            this.max_invite_visit = max_invite_visit;
        }

        public String getMax_invite_reg() {
            return max_invite_reg;
        }

        public void setMax_invite_reg(String max_invite_reg) {
            this.max_invite_reg = max_invite_reg;
        }
    }
}
