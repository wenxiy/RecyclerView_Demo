package com.example.recyclerview_demo;

public class Developer {

    /**
     * username : google
     * name : Google
     * type : organization
     * url : https://github.com/google
     * avatar : https://avatars0.githubusercontent.com/u/1342004
     * repo : {"name":"material-design-icons","description":"Material Design icons by Google","url":"https://github.com/google/material-design-icons"}
     */

    private String username;
    private String name;
    private String type;
    private String url;
    private String avatar;
    private RepoBean repo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RepoBean getRepo() {
        return repo;
    }

    public void setRepo(RepoBean repo) {
        this.repo = repo;
    }

    public static class RepoBean {
        /**
         * name : material-design-icons
         * description : Material Design icons by Google
         * url : https://github.com/google/material-design-icons
         */

        private String name;
        private String description;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
