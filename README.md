# IMDb Season Performance Tracker
While IMDb allows you to view the rating of your favorite TV shows, it doesn’t provide insights into the performance across different seasons. This program is designed to fill that gap.

## How It Works
### 1. Find the IMDb Link of Your Favorite Show
For example, for "Game of Thrones," the IMDb link is: https://www.imdb.com/title/tt0944947/?ref_=nv_sr_srsg_1_tt_7_nm_0_in_0_q_game%2520of
### 2. Extract the TV Show ID
From this link, you only need the part between title/ and /. In this case, it's tt0944947.
### 3. Update the Code
Paste the extracted TV Show ID into the Main class:
```
String tvShowId = "tt0944947";
```
### 4. Run the Program
Execute the Main class. After running the program, a graph of IMDb ratings across seasons for the specified TV show will be generated and saved in the src/main/resources/Charts directory.

For example, below is the graph for "Game of Thrones," which visually represents the show's fluctuating ratings across its seasons.
<div>
<img src="https://github.com/user-attachments/assets/e815efa9-60c4-42d5-bac1-ed2f2a50100f" alt="tt0944947_chart" width="600">
</div>


With this tool, you can easily track the performance of your favorite TV shows across different seasons.
