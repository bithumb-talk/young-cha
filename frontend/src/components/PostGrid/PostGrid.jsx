/* eslint-disable react/destructuring-assignment */
/* eslint-disable react/jsx-key */
import React from 'react';
import PropTypes from 'prop-types';
import Grid from '@mui/material/Grid';
import PostCard from '../PostCard/PostCard';

function PostGrid(props) {
  const postArr = props.postItem;
  const [postGridItem, setPostGridItem] = React.useState(postArr);

  React.useEffect(() => {
    setPostGridItem(postArr);
  }, [postArr]);

  return (
    <div
      style={{
        overflow: 'hidden',
        display: 'flex',
        flexWrap: 'wrap',
        margin: '-1%',
        paddingLeft: '3%',
        paddingRight: '3%',
        flexDirection: 'column',
      }}
    >
      <Grid container spacing={3}>
        {postGridItem.map((item) => (
          <Grid item lg={3} md={4} sm={6} xs={12}>
            <PostCard
              boardTitle={item.boardTitle}
              boardNo={item.boardNo}
              boardCreatedDate={item.boardCreatedDate}
              boardImg={item.boardImg}
              boardContent={item.boardContent}
              nickname={item.nickname}
              links={item.links[0].href}
            />
          </Grid>
        ))}
      </Grid>
    </div>
  );
}

export default PostGrid;

PostGrid.propTypes = {
  postItem: PropTypes.elementType.isRequired,
};
