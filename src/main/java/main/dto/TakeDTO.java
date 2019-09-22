package main.dto;

public class TakeDTO {
    public TakeDTO(){}

    private String imageUrl;
    private String repoTitle;
    private String repoDescription;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRepoTitle() {
        return repoTitle;
    }

    public void setRepoTitle(String repoTitle) {
        this.repoTitle = repoTitle;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }
}
