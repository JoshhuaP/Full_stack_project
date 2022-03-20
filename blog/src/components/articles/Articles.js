export default function Articles(props) {

    const articlesElements = props.data.map(i => <div key={i.id} className="articles__item" >
        <h3 className="article__category">{i.categoryId}</h3>
        <h3 className="article__authorName">{i.author}</h3>
        <h3 className="article__date">{i.date}</h3>
        <h3 className="article__content">{i.content}</h3>

        <button className="article__deleteButton" onClick={event => props.deleteArticle(event, i.id)}>Delete</button>
    </div>)

    return (
        <div className="articles">
            <h1 className='articles__title'>Articles</h1>
            <div className='articles__container'>
                {articlesElements}
            </div>
        </div>
    )
}